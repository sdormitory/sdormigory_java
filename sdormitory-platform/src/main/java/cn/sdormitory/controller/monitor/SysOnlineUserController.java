package cn.sdormitory.controller.monitor;

import cn.sdormitory.common.api.CommonPage;
import cn.sdormitory.common.api.CommonResult;
import cn.sdormitory.sys.service.OnlineUserService;
import cn.sdormitory.sys.vo.OnlineUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: SysOnlineUserController
 */
@RestController
@Api(tags = "Monitor-online => 在线用户管理")
@RequestMapping("/monitor/online")
public class SysOnlineUserController {
    @Resource
    private OnlineUserService onlineUserService;

    @ApiOperation("list => 根据关键字分页获取用户列表")
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<OnlineUser>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                     @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<OnlineUser> onlineUsers = onlineUserService.getAll(keyword);
        return CommonResult.success(CommonPage.listToPage(pageNum , pageSize, onlineUsers));

    }

}

