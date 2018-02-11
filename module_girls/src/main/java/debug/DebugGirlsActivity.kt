package debug

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.junlong0716.module.girls.R

/**
 *@author: 巴黎没有摩天轮Li
 *@description:
 *@date: Created in 下午1:15 2018/1/1
 *@modified by:
 */
@Route(path = "/module_girls/DebugGirlsActivity")
class DebugGirlsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.debug_activity_girls)
    }
}
