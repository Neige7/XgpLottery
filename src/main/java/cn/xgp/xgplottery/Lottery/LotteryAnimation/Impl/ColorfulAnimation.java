package cn.xgp.xgplottery.Lottery.LotteryAnimation.Impl;

import cn.xgp.xgplottery.Gui.Impl.Anim.ColorfulAnimGui;
import cn.xgp.xgplottery.Listener.CloseListener;
import cn.xgp.xgplottery.Lottery.Lottery;
import cn.xgp.xgplottery.Lottery.LotteryAnimation.LotteryAnimation;
import cn.xgp.xgplottery.Utils.LangUtils;
import cn.xgp.xgplottery.XgpLottery;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ColorfulAnimation extends LotteryAnimation {


    public ColorfulAnimation(Player player, Lottery lottery) {
        super(player, lottery);
    }

    @Override
    public String toLore() {
        return LangUtils.ColorfulAnimation;
    }

    @Override
    public void playAnimation() {
        awards.add(getOneAward());
        ColorfulAnimGui gui = new ColorfulAnimGui(calculator.isSpecial(),awards.get(0).getRecordDisplayItem());
        player.openInventory(gui.getInventory());
        taskID = XgpLottery.foliaLibAPI.getScheduler().runTaskLater(()->player.closeInventory(),4000L);
        CloseListener closeListener = new CloseListener(taskID,player.getUniqueId(),this,true);
        Bukkit.getPluginManager().registerEvents(closeListener, XgpLottery.instance);
    }
}
