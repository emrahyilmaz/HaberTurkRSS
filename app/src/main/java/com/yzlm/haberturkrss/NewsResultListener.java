package com.yzlm.haberturkrss;

import java.util.List;

/**
 * Created by yzlm on 13.06.2017.
 */

public interface NewsResultListener {
    void onSuccess(List<News> news);
    void onFail(String errorMessage);
}
