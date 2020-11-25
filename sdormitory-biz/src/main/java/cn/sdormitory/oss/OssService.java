package cn.sdormitory.oss;


import cn.sdormitory.oss.vo.OssCallbackResult;
import cn.sdormitory.oss.vo.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

/**
 * oss上传管理Service
 * Created by macro on 2018/5/17.
 */

/**
 * ClassName: MybatisPlusConfig
 */
public interface OssService {
    /**
     * oss上传策略生成
     *
     * @return
     */
    OssPolicyResult policy();

    /**
     * oss上传成功回调
     *
     * @param request
     * @return
     */
    OssCallbackResult callback(HttpServletRequest request);
}
