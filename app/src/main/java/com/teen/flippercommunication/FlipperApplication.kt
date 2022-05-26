package com.teen.flippercommunication

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils.shouldEnableFlipper
import com.facebook.flipper.core.FlipperClient
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.soloader.SoLoader

class FlipperApplication :Application (){
    var custom  = FlipperCustomPlugin()
    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this, false)
        if (BuildConfig.DEBUG && shouldEnableFlipper(this)) {
            val client: FlipperClient = AndroidFlipperClient.getInstance(this)
            client.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))
            client.addPlugin(custom)
            client.start()
        }
    }


}