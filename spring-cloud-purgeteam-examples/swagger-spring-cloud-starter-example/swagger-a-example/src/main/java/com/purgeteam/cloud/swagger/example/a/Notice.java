package com.purgeteam.cloud.swagger.example.a;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 实体类
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("公告")
public class Notice {

    /**
     * ID
     */
    @ApiModelProperty("id")
    private Integer id;

    /**
     * 公告内容
     */
    @ApiModelProperty("公告内容")
    private String content;

}
