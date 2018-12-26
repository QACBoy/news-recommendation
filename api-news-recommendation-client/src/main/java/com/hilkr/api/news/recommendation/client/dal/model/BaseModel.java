package com.hilkr.api.news.recommendation.client.dal.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/***
 *
 * 描述：
 *
 * @author sam
 * @date 2018/12/26
 *
 */
@JsonIgnoreProperties(
        ignoreUnknown = true
)
@Getter
@Setter
public abstract class BaseModel implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String remark;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

    private Integer delFlg;

    protected abstract Serializable pkVal();
}
