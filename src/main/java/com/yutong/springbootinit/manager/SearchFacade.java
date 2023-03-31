package com.yutong.springbootinit.manager;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yutong.springbootinit.common.BaseResponse;
import com.yutong.springbootinit.common.ErrorCode;
import com.yutong.springbootinit.common.ResultUtils;
import com.yutong.springbootinit.datasource.*;
import com.yutong.springbootinit.exception.ThrowUtils;
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
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaoyu
 * @date 2023/3/22
 * @apiNote
 */
@Component
@Slf4j
public class SearchFacade {

    @Resource
    private PictureDataSource pictureDataSource;

    @Resource
    private UserDataSource userDataSource;

    @Resource
    private PostDataSource postDataSource;

    @Resource
    private DataSourceRegistry dataSourceRegistry;
    public SearchVO searchAll(@RequestBody SearchRequest searchRequest, HttpServletRequest request) {
        String type = searchRequest.getType();
        SearchTypeEnum searchTypeEnum = SearchTypeEnum.getEnumByValue(type);
        ThrowUtils.throwIf(StringUtils.isBlank(type), ErrorCode.PARAMS_ERROR);
        String searchText = searchRequest.getSearchText();
        long current = searchRequest.getCurrent();
        long pageSize = searchRequest.getPageSize();
        if (searchTypeEnum == null) {
            Page<Picture> picturePage = pictureDataSource.doSearch(searchText, current, pageSize);

            UserQueryRequest userQueryRequest = new UserQueryRequest();
            userQueryRequest.setUserName(searchText);
            Page<UserVO> userVOPage = userDataSource.doSearch(searchText, current, pageSize);

            PostQueryRequest postQueryRequest = new PostQueryRequest();
            postQueryRequest.setSearchText(searchText);
            Page<PostVO> postVOPage = postDataSource.doSearch(searchText, current, pageSize);

            SearchVO searchVO = new SearchVO();
            searchVO.setUserList(userVOPage.getRecords());
            searchVO.setPictureList(picturePage.getRecords());
            searchVO.setPostList(postVOPage.getRecords());
            return searchVO;
        } else {

            SearchVO searchVO = new SearchVO();
            DataSource dataSource = dataSourceRegistry.getDataSourceByType(type);
            Page page = dataSource.doSearch(searchText, current, pageSize);
            searchVO.setDataList(page.getRecords());
            return searchVO;
        }

    }

}
