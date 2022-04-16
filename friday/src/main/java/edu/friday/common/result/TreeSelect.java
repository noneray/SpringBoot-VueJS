package edu.friday.common.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.friday.model.vo.SysMenuVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 树型结构实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeSelect implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 节点ID
     */
    private Long id;
    /**
     * 节点名称
     */
    private String label;
    /**
     * 子节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeSelect> children;

    public TreeSelect(SysMenuVO menu) {
        this.id = menu.getMenuId();
        this.label = menu.getMenuName();
        this.children = menu.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }

}
