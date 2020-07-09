package org.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@JsonDeserialize(builder = ItemModel.ItemModelBuilder.class)
@ApiModel(description = "Item model")
@Validated
public class ItemModel {
    @ApiModelProperty(value = "Uniq ID of item", example = "1")
    @Min(value = 1, message = "Id should be greater then 0")
    private final Integer id;

    @ApiModelProperty("Name of item")
    @NotNull
    @Size(max = 30)
    private final String name;

    @ApiModelProperty("Some long item description")
    @Size(max = 1024)
    private final String description;

    @ApiModelProperty(value = "Count of items", example = "1")
    @Min(0)
    private final Integer count;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ItemModelBuilder {

    }
}