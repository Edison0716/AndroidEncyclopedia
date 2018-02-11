package debug

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.htxcsoft.module_photo.ExhibitPhotoActivity
import com.junlong0716.module.picture.R

class DebugExhibitPhotoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debug_exhibit_photo)
        var bundle = Bundle()
        bundle.putString("picUrl", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1518344524699&di=645d0ae4115aaa2de1e420dcab852666&imgtype=0&src=http%3A%2F%2Fimg.idol001.com%2Forigin%2F2018%2F01%2F25%2F99bd852a32d9370fa7c1663a7f954b8f1516877063_watermark.jpg")
        var intent = Intent(this, ExhibitPhotoActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
        finish()
    }
}
