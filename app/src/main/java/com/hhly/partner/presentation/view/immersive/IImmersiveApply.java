package com.hhly.partner.presentation.view.immersive;

/**
 * Created by Simon on 16/9/7.
 */
public interface IImmersiveApply {

    /**
     * 内容是否需要显示在状态栏后
     * @return
     */
    boolean applyImmersive();

    /**
     * 是否要监听滚动, 需要配合ToolbarHelper#applyScroll使用
     * @return
     */
    boolean applyScroll();

    /**
     * Toolbar 初始透明度 0.0-1.0
     * @return 0.0代表透明 1.0代表不透明
     */
    float initAlpha();

}
