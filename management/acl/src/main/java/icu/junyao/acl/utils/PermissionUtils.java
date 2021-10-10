package icu.junyao.acl.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import icu.junyao.acl.entity.AclPermission;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author johnson
 * @since 2021-10-04
 */
public class PermissionUtils {

    /**
     * 递归构建子权限
     *
     * @param permissionNode 当前权限节点
     * @param permissionList 待构建的权限列表
     * @return 构建好的当前权限节点
     */
    private static AclPermission selectChildren(AclPermission permissionNode, List<AclPermission> permissionList) {
        //1 因为向一层菜单里面放二层菜单，二层里面还要放三层，把对象初始化
        permissionNode.setChildren(new ArrayList<>());

        //2 遍历所有菜单list集合，进行判断比较，比较id和pid值是否相同
        for (AclPermission it : permissionList) {
            //判断 id和pid值是否相同
            if (permissionNode.getId().equals(it.getPid())) {
                //把父菜单的level值+1
                int level = permissionNode.getLevel() + 1;
                it.setLevel(level);
                //把查询出来的子菜单放到父菜单里面
                permissionNode.getChildren().add(selectChildren(it, permissionList));
            }
        }
        return permissionNode;
    }

    /**
     * 构建权限树
     *
     * @param permissionList 待构建的权限集合
     * @return 构建好的权限集合
     */
    public static List<AclPermission> buildPermission(List<AclPermission> permissionList) {
        //创建list集合，用于数据最终封装
        List<AclPermission> finalNode = new ArrayList<>();
        //把所有菜单list集合遍历，得到顶层菜单 pid=0菜单，设置level是1
        for (AclPermission permissionNode : permissionList) {
            //得到顶层菜单 pid=0菜单
            if ("0".equals(permissionNode.getPid())) {
                //设置顶层菜单的level是1
                permissionNode.setLevel(1);
                //根据顶层菜单，向里面进行查询子菜单，封装到finalNode里面
                finalNode.add(selectChildren(permissionNode, permissionList));
            }
        }
        return finalNode;
    }

    /**
     * 构建菜单列表
     * @param treeNodes 已构建好的权限树
     * @return 菜单列表
     */
    public static List<JSONObject> buildMenu(List<AclPermission> treeNodes) {
        List<JSONObject> menus = new ArrayList<>();
        if (treeNodes.size() == 1) {
            AclPermission topNode = treeNodes.get(0);
            //左侧一级菜单
            List<AclPermission> oneMenuList = topNode.getChildren();
            for (AclPermission one : oneMenuList) {
                JSONObject oneMenu = new JSONObject();
                oneMenu.put("path", one.getPath());
                oneMenu.put("component", one.getComponent());
                oneMenu.put("redirect", "noredirect");
                oneMenu.put("name", "name_" + one.getId());
                oneMenu.put("hidden", false);

                JSONObject oneMeta = new JSONObject();
                oneMeta.put("title", one.getName());
                oneMeta.put("icon", one.getIcon());
                oneMenu.put("meta", oneMeta);

                List<JSONObject> children = new ArrayList<>();
                List<AclPermission> twoMenuList = one.getChildren();
                for (AclPermission two : twoMenuList) {
                    JSONObject twoMenu = new JSONObject();
                    twoMenu.put("path", two.getPath());
                    twoMenu.put("component", two.getComponent());
                    twoMenu.put("name", "name_" + two.getId());
                    twoMenu.put("hidden", false);

                    JSONObject twoMeta = new JSONObject();
                    twoMeta.put("title", two.getName());
                    twoMenu.put("meta", twoMeta);

                    children.add(twoMenu);

                    List<AclPermission> threeMenuList = two.getChildren();
                    for (AclPermission three : threeMenuList) {
                        if (StrUtil.isEmpty(three.getPath())) {
                            continue;
                        }

                        JSONObject threeMenu = new JSONObject();
                        threeMenu.put("path", three.getPath());
                        threeMenu.put("component", three.getComponent());
                        threeMenu.put("name", "name_" + three.getId());
                        threeMenu.put("hidden", true);

                        JSONObject threeMeta = new JSONObject();
                        threeMeta.put("title", three.getName());
                        threeMenu.put("meta", threeMeta);

                        children.add(threeMenu);
                    }
                }
                oneMenu.put("children", children);
                menus.add(oneMenu);
            }
        }
        return menus;
    }
}
