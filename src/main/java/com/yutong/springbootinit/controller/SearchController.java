package com.yutong.springbootinit.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.yutong.springbootinit.common.BaseResponse;
import com.yutong.springbootinit.common.ErrorCode;
import com.yutong.springbootinit.common.ResultUtils;
import com.yutong.springbootinit.exception.ThrowUtils;
import com.yutong.springbootinit.manager.SearchFacade;
import com.yutong.springbootinit.model.dto.picture.PictureQueryRequest;
import com.yutong.springbootinit.model.dto.post.PostQueryRequest;
import com.yutong.springbootinit.model.dto.search.SearchRequest;
import com.yutong.springbootinit.model.dto.user.UserQueryRequest;
import com.yutong.springbootinit.model.entity.Picture;
import com.yutong.springbootinit.model.enums.SearchTypeEnum;
import com.yutong.springbootinit.model.vo.PostVO;
import com.yutong.springbootinit.model.vo.SearchVO;
import com.yutong.springbootinit.model.vo.UserVO;
import com.yutong.springbootinit.service.PictureService;
import com.yutong.springbootinit.service.PostService;
import com.yutong.springbootinit.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/search")
@Slf4j
public class SearchController {

    @Resource
    private PictureService pictureService;

    @Resource
    private UserService userService;

    @Resource
    private PostService postService;

    @Resource
    private SearchFacade searchFacade;

    @PostMapping("/all")
    public BaseResponse<SearchVO> searchAll(@RequestBody SearchRequest searchRequest, HttpServletRequest request) {
        return ResultUtils.success(searchFacade.searchAll(searchRequest, request));
    }


}
