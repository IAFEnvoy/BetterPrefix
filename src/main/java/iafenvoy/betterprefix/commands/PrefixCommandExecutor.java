package iafenvoy.betterprefix.commands;

import iafenvoy.betterprefix.prefix.PPlayer;
import iafenvoy.betterprefix.prefix.Prefix;
import iafenvoy.betterprefix.prefix.PrefixManager;
import iafenvoy.betterprefix.utils.UUIDUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class PrefixCommandExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        /*
        玩家指令：（Player权限级别）
        /prefix list :列出所有可用前缀，格式为<id>-<text>（如为镇长权限使用红色标出，如为OP则为显示所有前缀）
        /prefix set <id> :设置前缀
        /prefix clear :清除前缀
        镇长指令：（Helper权限级别）
        /prefix addPlayer <id> <playerName> :添加前缀的可用玩家
        /prefix removePlayer <id> <playerName>
        /prefix addHelper <id> <playerName> :添加可以操作这个前缀的玩家（操作可用玩家）
        /prefix removeHelper <id> <playerName>
        OP指令：（OP权限级别）
        /prefix add <id> <text> :添加一个前缀
        /prefix remove <id> :按id删除前缀
        /prefix modify <id> <text> :修改指定前缀的显示文本
        * */
        if (args.length == 0) return false;
        final String key = args[0];
        //Player command
        if (key.equals("list")) {
            if (sender instanceof Player) {
                UUID uuid = UUIDUtils.getUuidByName(sender.getName());
                if (PrefixManager.INSTANCE.isOP(uuid))
                    for (Prefix prefix : PrefixManager.INSTANCE.getPrefixes())
                        sender.sendMessage(prefix.getId() + "-" + prefix.getText());
                else
                    for (Prefix prefix : PrefixManager.INSTANCE.getPlayerByUuid(uuid).getCanUse()) {
                        ChatColor color = prefix.getAdmins().contains(uuid) ? ChatColor.RED : ChatColor.WHITE;
                        sender.sendMessage(color + prefix.getId() + "-" + prefix.getText());
                    }
            } else
                for (Prefix prefix : PrefixManager.INSTANCE.getPrefixes())
                    sender.sendMessage(prefix.getId() + "-" + prefix.getText());
        } else if (key.equals("set")) {
            if (args.length == 1) return false;
            if (sender instanceof Player) {
                UUID uuid = UUIDUtils.getUuidByName(sender.getName());
                final String id = args[1];
                boolean isValidFlag = false;
                for (Prefix prefix : PrefixManager.INSTANCE.getPlayerByUuid(uuid).getCanUse())
                    if (prefix.getId().equals(id)) {
                        PrefixManager.INSTANCE.getPlayerByUuid(uuid).setNowUse(prefix);
                        isValidFlag = true;
                        break;
                    }
                if (!isValidFlag)
                    sender.sendMessage("未找到指定前缀或无权限使用");
                else
                    sender.sendMessage("成功应用前缀");
            } else
                sender.sendMessage("这个指令只能由玩家执行！");
        } else if (key.equals("clear")) {
            if (sender instanceof Player) {
                UUID uuid = UUIDUtils.getUuidByName(sender.getName());
                PrefixManager.INSTANCE.getPlayerByUuid(uuid).setNowUse(null);
                sender.sendMessage("清除成功");
            } else
                sender.sendMessage("这个指令只能由玩家执行！");
        }
        //Helper command
        else if (key.equals("addPlayer")) {
            if (sender instanceof Player)
                if (args.length == 1) sender.sendMessage("缺少<id>参数");
                else if (args.length == 2) sender.sendMessage("缺少<playerName>参数");
                else {
                    final String id = args[1], name = args[2];
                    UUID uuid = UUIDUtils.getUuidByName(sender.getName());
                    Prefix prefix = PrefixManager.INSTANCE.getPrefixById(id);
                    if (prefix == null)
                        sender.sendMessage("未找到指定前缀！");
                    else if (prefix.isAdmin(uuid) || PrefixManager.INSTANCE.isOP(uuid)) {
                        PPlayer p = PrefixManager.INSTANCE.getPlayerByName(name);
                        if (p == null)
                            sender.sendMessage("玩家不存在！");
                        else {
                            p.addCanUse(prefix);
                            sender.sendMessage("添加成功");
                        }
                    } else
                        sender.sendMessage("你没有权限操作此前缀！");
                }
            else
                sender.sendMessage("这个指令只能由玩家执行！");
        } else if (key.equals("removePlayer")) {
            if (sender instanceof Player)
                if (args.length == 1) sender.sendMessage("缺少<id>参数");
                else if (args.length == 2) sender.sendMessage("缺少<playerName>参数");
                else {
                    final String id = args[1], name = args[2];
                    UUID uuid = UUIDUtils.getUuidByName(sender.getName());
                    Prefix prefix = PrefixManager.INSTANCE.getPrefixById(id);
                    if (prefix == null)
                        sender.sendMessage("未找到指定前缀！");
                    else if (prefix.isAdmin(uuid) || PrefixManager.INSTANCE.isOP(uuid)) {
                        PPlayer p = PrefixManager.INSTANCE.getPlayerByName(name);
                        if (p == null)
                            sender.sendMessage("玩家不存在！");
                        else {
                            p.removeCanUse(prefix);
                            sender.sendMessage("移除成功");
                        }
                    } else
                        sender.sendMessage("你没有权限操作此前缀！");
                }
            else
                sender.sendMessage("这个指令只能由玩家执行！");
        } else if (key.equals("addHelper")) {
            if (sender instanceof Player)
                if (args.length == 1) sender.sendMessage("缺少<id>参数");
                else if (args.length == 2) sender.sendMessage("缺少<playerName>参数");
                else {
                    final String id = args[1], name = args[2];
                    UUID uuid = UUIDUtils.getUuidByName(sender.getName());
                    Prefix prefix = PrefixManager.INSTANCE.getPrefixById(id);
                    if (prefix == null)
                        sender.sendMessage("未找到指定前缀！");
                    else if (prefix.isAdmin(uuid) || PrefixManager.INSTANCE.isOP(uuid)) {
                        PPlayer p = PrefixManager.INSTANCE.getPlayerByName(name);
                        if (p == null)
                            sender.sendMessage("玩家不存在！");
                        else {
                            prefix.addAdmin(p.getUuid());
                            sender.sendMessage("添加成功");
                        }
                    } else
                        sender.sendMessage("你没有权限操作此前缀！");
                }
            else
                sender.sendMessage("这个指令只能由玩家执行！");
        } else if (key.equals("removeHelper")) {
            if (sender instanceof Player)
                if (args.length == 1) sender.sendMessage("缺少<id>参数");
                else if (args.length == 2) sender.sendMessage("缺少<playerName>参数");
                else {
                    final String id = args[1], name = args[2];
                    UUID uuid = UUIDUtils.getUuidByName(sender.getName());
                    Prefix prefix = PrefixManager.INSTANCE.getPrefixById(id);
                    if (prefix == null)
                        sender.sendMessage("未找到指定前缀！");
                    else if (prefix.isAdmin(uuid) || PrefixManager.INSTANCE.isOP(uuid)) {
                        PPlayer p = PrefixManager.INSTANCE.getPlayerByName(name);
                        if (p == null)
                            sender.sendMessage("玩家不存在！");
                        else {
                            prefix.removeAdmin(p.getUuid());
                            sender.sendMessage("移除");
                        }
                    } else
                        sender.sendMessage("你没有权限操作此前缀！");
                }
            else
                sender.sendMessage("这个指令只能由玩家执行！");
        }
        //OP command
        else if (key.equals("add")) {
            if (sender instanceof Player) {
                PPlayer player = PrefixManager.INSTANCE.getPlayerByName(sender.getName());
                if (player.isOp()) {
                    if (args.length == 1) sender.sendMessage("缺少<id>参数");
                    else if (args.length == 2) sender.sendMessage("缺少<text>参数");
                    else {
                        final String id = args[1], text = args[2];
                        if (PrefixManager.INSTANCE.addPrefix(id, text))
                            sender.sendMessage("添加成功");
                        else
                            sender.sendMessage("此前缀id已存在！");
                    }
                } else
                    sender.sendMessage("你没有权限新建前缀！");
            } else
                sender.sendMessage("这个指令只能由玩家执行！");
        } else if (key.equals("remove")) {
            if (sender instanceof Player) {
                PPlayer player = PrefixManager.INSTANCE.getPlayerByName(sender.getName());
                if (player.isOp()) {
                    if (args.length == 1) sender.sendMessage("缺少<id>参数");
                    else {
                        final String id = args[1];
                        if (PrefixManager.INSTANCE.removePrefix(id))
                            sender.sendMessage("删除成功");
                        else
                            sender.sendMessage("未找到此前缀！");
                    }
                } else
                    sender.sendMessage("你没有权限删除前缀！");
            } else
                sender.sendMessage("这个指令只能由玩家执行！");
        } else if (key.equals("modify")) {
            if (sender instanceof Player) {
                PPlayer player = PrefixManager.INSTANCE.getPlayerByName(sender.getName());
                if (player.isOp()) {
                    if (args.length == 1) sender.sendMessage("缺少<id>参数");
                    else if (args.length == 2) sender.sendMessage("缺少<text>参数");
                    else {
                        final String id = args[1], text = args[2];
                        if (PrefixManager.INSTANCE.modifyPrefix(id, text))
                            sender.sendMessage("修改成功");
                        else
                            sender.sendMessage("前缀不存在！");
                    }
                } else
                    sender.sendMessage("你没有权限修改前缀！");
            } else
                sender.sendMessage("这个指令只能由玩家执行！");
        }
        return true;
    }
}
