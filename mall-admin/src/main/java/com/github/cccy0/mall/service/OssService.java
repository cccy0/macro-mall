package com.github.cccy0.mall.service;

import com.github.cccy0.mall.dto.OssCallbackResult;
import com.github.cccy0.mall.dto.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

/**
 * oss上传管理Service
 *
 * @author cy
 */
public interface OssService {
    /**
     * oss上传策略生成
     *
     * @return OssPolicyResult
     */
    OssPolicyResult policy();

    /**
     * oss上传成功回调
     *
     * @param request request
     * @return OssCallbackResult
     */
    OssCallbackResult callback(HttpServletRequest request);
}
