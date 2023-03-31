package com.yutong.springbootinit.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaoyu
 * @date 2023/3/21
 * @apiNote
 */
@Data
public class Picture implements Serializable {

    private String title;

    private String url;
}
