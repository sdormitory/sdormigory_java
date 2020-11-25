package cn.sdormitory.controller.basedata;

import cn.sdormitory.basedata.entity.BClass;
import cn.sdormitory.basedata.entity.BDormitory;
import cn.sdormitory.basedata.entity.BStudent;
import cn.sdormitory.basedata.service.BClassService;
import cn.sdormitory.basedata.service.BDormitoryService;
import cn.sdormitory.basedata.service.BStudentService;
import cn.sdormitory.common.annotation.IgnoreAuth;
import cn.sdormitory.common.annotation.SysLog;
import cn.sdormitory.common.api.CommonPage;
import cn.sdormitory.common.api.CommonResult;
import cn.sdormitory.common.api.UploadResult;
import cn.sdormitory.common.enums.BusinessType;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/10 16:46
 * @version：V1.0
 */
@RestController
@Api(tags = "Basedata-bstudent=> 学员管理")
@RequestMapping("/basedata/bstudent")
public class BStudentController {
    @Autowired
    private BStudentService bStudentService;
    @Autowired
    private BClassService bClassService;
    @Autowired
    private BDormitoryService bDormitoryService;

    @ApiOperation("list => 根据条件分页获取学员列表")
    @PreAuthorize("@ss.hasPermi('basedata:bstudent:list')")
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<BStudent>> list(@RequestParam Map<String, Object> params) {
        IPage<BStudent> page = bStudentService.getPage(params);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiOperation("{id} => 学员信息")
    @PreAuthorize("@ss.hasPermi('basedata:bstudent:query')")
    @GetMapping("/{id}")
    public CommonResult<BStudent> info(@PathVariable("id") Long id) throws Exception {
        return CommonResult.success(bStudentService.getBStudentById(id));
    }

    @IgnoreAuth
    @ApiOperation("create => 新建学员信息")
    @PreAuthorize("@ss.hasPermi('basedata:bstudent:add')")
    @SysLog(title = "学员管理", businessType = BusinessType.INSERT)
    @PostMapping(value = "/create")
    public CommonResult<Integer> create(@RequestParam(value = "photo",required = false) MultipartFile photo,@RequestBody BStudent bStudent) throws IOException {
        BStudent bStudentInfo = bStudentService.getByStudentNo(bStudent.getStudentNo());
        System.out.println("............. photo : "+photo);
        if (bStudentInfo != null && !Objects.equals(bStudentInfo.getId(), bStudent.getId())) {
            return CommonResult.failed("该学号已存在");
        }
        //根据班级ID查询班级名称
        BClass bClass=bClassService.getBClassById(bStudent.getClassId());
        bStudent.setClassName(bClass.getClassName());

        int count = bStudentService.create(bStudent);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("update/{id} => 修改学员信息")
    @PreAuthorize("@ss.hasPermi('basedata:bstudent:edit')")
    @SysLog(title = "学员宿舍管理", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/update/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, @RequestBody BStudent bStudent) {
        //根据班级ID查询班级名称
        BClass bClass=bClassService.getBClassById(bStudent.getClassId());
        bStudent.setClassName(bClass.getClassName());

        int count = bStudentService.update(id, bStudent);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("deleteByIds/{ids} => 删除指定学员信息")
    @PreAuthorize("@ss.hasPermi('basedata:bstudent:remove')")
    @SysLog(title = "学员管理", businessType = BusinessType.DELETE)
    @DeleteMapping(value = "/deleteByIds/{ids}")
    public CommonResult<Integer> deleteByIds(@PathVariable Long[] ids) {
        int count = bStudentService.deleteByIds(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("update/status/{id} => 修改学员状态")
    @PreAuthorize("@ss.hasPermi('basedata:bstudent:edit')")
    @SysLog(title = "学员管理", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/update/status/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, String status) {
        int count = bStudentService.updateStatus(id, status);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    //上传学员照片
//    @IgnoreAuth
    @PostMapping("/upload")
    public UploadResult upload(MultipartFile photo, HttpServletRequest request) throws IOException {
        InputStream inputStream=photo.getInputStream();
        byte[] iconData=new byte[(int)photo.getSize()];
        inputStream.read(iconData);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        UploadResult result = new UploadResult();

        //获取文件上传地址的真实路径
        String realPath =request.getSession().getServletContext().getRealPath("/uploadFile/");
        System.out.println("---------- "+request.getSession().getServletContext()
                .getRealPath("/"));
        String format = sdf.format(new Date());
        //构建文件对象
        File folder = new File(realPath+format);
        if(!folder.isDirectory()){ // 判断目录是否存在
            folder.mkdirs();  // 创建目录
        }
        // 获取上传文件的逻辑名称
        String oldName = photo.getOriginalFilename();
        // 给文件重命名，避免文件重名
        String newName = UUID.randomUUID().toString()+oldName
                .substring(oldName.lastIndexOf("."),oldName.length());
        try{
            // 上传文件至指定的目录
            photo.transferTo(new File(folder,newName));
            //获取文件上传后的完整地址
            String filePath = request.getScheme()+"://"+
                    request.getServerName()+":"+request.getServerPort()+""+request.getContextPath()+
                    "/uploadFile/"+format+newName;
            System.out.println("filePath:"+filePath);
            result.setSuccess(true);
            result.setMessage(filePath);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * 文件上传（表单和文件同步上传）
     *
     * @returncreate
     */
    @PostMapping(value = "/createStu")
    public CommonResult saveStu(@RequestParam(value = "upload") MultipartFile upload, BStudent bStudent) {
        if (upload.isEmpty()) {
            return CommonResult.failed("未上传照片");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        BStudent bStudentInfo = bStudentService.getByStudentNo(bStudent.getStudentNo());
        if (bStudentInfo != null && !Objects.equals(bStudentInfo.getId(), bStudent.getId())) {
            return CommonResult.failed("该学号已存在");
        }
        byte[] pictureData = null;
        // 使用输入流获取上传的图片
        InputStream inputStream = null;
        try {
            inputStream = upload.getInputStream();
            // 定义字节数组用于存放图片信息
            pictureData = new byte[(int) upload.getSize()];
            // 读取图片到字节数组中
            inputStream.read(pictureData);
        } catch (IOException e) {
            e.printStackTrace();
        }


        bStudent.setPhoto(pictureData);
        //根据班级ID查询班级名称
        BClass bClass = bClassService.getBClassById(bStudent.getClassId());
        bStudent.setClassName(bClass.getClassName());
        //根据宿舍ID查询宿舍号
        BDormitory bDormitory=bDormitoryService.getBDormitoryById(Long.parseLong(bStudent.getBdormitoryId()));
        bStudent.setDormitoryNo(bDormitory.getDormitoryNo());

        int count = bStudentService.create(bStudent);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 获取图片
     *
     * @param id
     * @param width
     * @param height
     * @param response
     * @throws IOException
     */
    @IgnoreAuth
    @GetMapping("/getPhoto.do")
    public void getPhotoById(Long id, int width, int height, final HttpServletResponse response) throws IOException {
        BStudent bStudentInfo = bStudentService.getBStudentById(id);
        byte[] data = bStudentInfo.getPhoto();
        if (width != 0 && height != 0) {
            data = scaleImage(data, width, height);
        }
        response.setContentType("image/jpeg");
        response.setCharacterEncoding("UTF-8");
        OutputStream outputSream = response.getOutputStream();
        InputStream in = new ByteArrayInputStream(data);
        int len = 0;
        byte[] buf = new byte[1024];
        while ((len = in.read(buf, 0, 1024)) != -1) {
            outputSream.write(buf, 0, len);
        }
        outputSream.close();
    }

    @ApiOperation("updateAndUpload/ => 修改学员信息")
    @PostMapping (value = "/updateAndUpload")
    public CommonResult<Integer> updateAndUpload(BStudent bStudent, @RequestParam(value = "upload") MultipartFile upload) {

        //根据班级ID查询班级名称
        BClass bClass = bClassService.getBClassById(bStudent.getClassId());
        bStudent.setClassName(bClass.getClassName());
        //根据宿舍ID查询宿舍号
        BDormitory bDormitory=bDormitoryService.getBDormitoryById(Long.parseLong(bStudent.getBdormitoryId()));
        bStudent.setDormitoryNo(bDormitory.getDormitoryNo());

        if (upload.isEmpty()) {
            int count = bStudentService.update(bStudent.getId(), bStudent);
        } else {
            byte[] pictureData = null;
            // 使用输入流获取上传的图片
            InputStream inputStream = null;
            try {
                inputStream = upload.getInputStream();
                // 定义字节数组用于存放图片信息
                pictureData = new byte[(int) upload.getSize()];
                // 读取图片到字节数组中
                inputStream.read(pictureData);
                bStudent.setPhoto(pictureData);
            } catch (IOException e) {
                e.printStackTrace();
            }
            int count = bStudentService.update(bStudent.getId(), bStudent);
            if (count > 0) {
                return CommonResult.success(count);
            }
        }
        return CommonResult.failed();
    }


    /**
     * 显示图片
     *
     * @param data   byet流
     * @param width  图片的宽度
     * @param height 图片的高度
     * @return 2020.11.19
     * @throws IOException
     */
    public static byte[] scaleImage(byte[] data, int width, int height) throws IOException {
        BufferedImage buffered_oldImage = ImageIO.read(new ByteArrayInputStream(data));
        int imageOldWidth = buffered_oldImage.getWidth();
        int imageOldHeight = buffered_oldImage.getHeight();
        double scale_x = (double) width / imageOldWidth;
        double scale_y = (double) height / imageOldHeight;
        double scale_xy = Math.min(scale_x, scale_y);
        int imageNewWidth = (int) (imageOldWidth * scale_xy);
        int imageNewHeight = (int) (imageOldHeight * scale_xy);
        BufferedImage buffered_newImage = new BufferedImage(imageNewWidth, imageNewHeight, BufferedImage.TYPE_INT_RGB);
        buffered_newImage.getGraphics().drawImage(buffered_oldImage.getScaledInstance(imageNewWidth, imageNewHeight, BufferedImage.SCALE_SMOOTH), 0, 0, null);
        buffered_newImage.getGraphics().dispose();
        ByteArrayOutputStream outPutStream = new ByteArrayOutputStream();
        ImageIO.write(buffered_newImage, "jpeg", outPutStream);
        return outPutStream.toByteArray();
    }
}
