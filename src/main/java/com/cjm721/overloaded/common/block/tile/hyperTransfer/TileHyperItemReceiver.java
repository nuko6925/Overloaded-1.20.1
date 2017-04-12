package com.cjm721.overloaded.common.block.tile.hyperTransfer;

import com.cjm721.overloaded.common.block.tile.hyperTransfer.base.AbstractTileHyperReceiver;
import com.cjm721.overloaded.common.storage.LongItemStack;
import com.cjm721.overloaded.common.storage.item.IHyperHandlerItem;

import static com.cjm721.overloaded.common.util.CapabilityHyperItem.HYPER_ITEM_HANDLER;


/**
 * Created by CJ on 4/8/2017.
 */
public class TileHyperItemReceiver extends AbstractTileHyperReceiver<LongItemStack,IHyperHandlerItem> {

    public TileHyperItemReceiver() {
        super(HYPER_ITEM_HANDLER);
    }
}