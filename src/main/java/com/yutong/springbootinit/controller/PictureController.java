package com.yutong.springbootinit.controller;

import co.elastic.clients.elasticsearch.sql.QueryRequest;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.yutong.springbootinit.annotation.AuthCheck;
import com.yutong.springbootinit.common.BaseResponse;
import com.yutong.springbootinit.common.DeleteRequest;
import com.yutong.springbootinit.common.ErrorCode;
import com.yutong.springbootinit.common.ResultUtils;
import com.yutong.springbootinit.constant.UserConstant;
import com.yutong.springbootinit.exception.BusinessException;
import com.yutong.springbootinit.exception.ThrowUtils;
import com.yutong.springbootinit.model.dto.picture.PictureQueryRequest;
import com.yutong.springbootinit.model.dto.post.PostAddRequest;
import com.yutong.springbootinit.model.dto.post.PostEditRequest;
import com.yutong.springbootinit.model.dto.post.PostQueryRequest;
import com.yutong.springbootinit.model.dto.post.PostUpdateRequest;
import com.yutong.springbootinit.model.entity.Picture;
import com.yutong.springbootinit.model.entity.Post;
import com.yutong.springbootinit.model.entity.User;
import com.yutong.springbootinit.model.vo.PostVO;
import com.yutong.springbootinit.service.PictureService;
import com.yutong.springbootinit.service.PostService;
import com.yutong.springbootinit.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 帖子接口
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@RestController
@RequestMapping("/picture")
@Slf4j
public class PictureController {

    @Resource
    private PictureService pictureService;
    private final static Gson GSON = new Gson();

    // region 增删改查

    /**
     * 分页获取列表（封装类）
     *
     * @param pictureQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<Picture>> listPictureByPage(@RequestBody PictureQueryRequest pictureQueryRequest,
                                                        HttpServletRequest request) {
        long current = pictureQueryRequest.getCurrent();
        long size = pictureQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        String searchText = pictureQueryRequest.getSearchText();

        Page<Picture> picturePage = pictureService.searchPicture(searchText, current, size);

        return ResultUtils.success(picturePage);
    }


}
