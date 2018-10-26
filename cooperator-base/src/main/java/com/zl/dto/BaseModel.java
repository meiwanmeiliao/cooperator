package com.zl.dto;


import javax.persistence.*;
import java.util.Date;

/**
 * @author zhuolin
 * @program: cooperator
 * @date 2018/10/25
 * @description: ${description}
 **/
@MappedSuperclass
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * 是否删除 （）
     */
    @Column(name = "is_deleted", nullable = false,
            columnDefinition = "bit(1) default 0")
    private Boolean deleted = false;
    /**
     * 更新时间
     */
    @Column(insertable = false)
    private Date updateTime = new Date();
    /**
     * 创建时间
     */
    @Column(nullable = false, updatable = false,
            columnDefinition = "dateTime default CURRENT_TIMESTAMP")
    private Date createTime = new Date();
    /**
     * 创建人
     */
    @Column(nullable = false, updatable = false)
    private String createBy;
    /**
     * 删除人
     */
    private String deletedBy;

    @Deprecated
    public Boolean getDeleted() {
        return deleted;
    }

    @Deprecated
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Deprecated
    public String getDeletedBy() {
        return deletedBy;
    }

    @Deprecated
    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }
}
