package com.example.sueobmwodeudji.adapter;

import android.widget.FrameLayout;

public class BasicFrame {
    /* 기본 */
    String title;
    FrameLayout frameLayout;
    public BasicFrame(String title) {
        this.title = title;
    }

    /* 활용 */

    // 인기 게시글
    String community_title;
    String post_title;
    public BasicFrame(String community_title, String post_title) {
        this.community_title = community_title;
        this.post_title = post_title;
    }

}
