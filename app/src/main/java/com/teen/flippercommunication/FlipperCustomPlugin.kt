package com.teen.flippercommunication

import com.facebook.flipper.core.FlipperConnection
import com.facebook.flipper.core.FlipperObject
import com.facebook.flipper.core.FlipperPlugin

class FlipperCustomPlugin : FlipperPlugin {
    private var id = 1
    private var connection: FlipperConnection? = null
    private var interfaceFlipper:com.teen.flippercommunication.FlipperPlugin? = null

    override fun getId(): String = "plugin-tut2"

    override fun onConnect(connection: FlipperConnection?) {
        this.connection = connection
        setConnectionReceive()
          newRow(FlipperObject.Builder()
                .put("id", 1)
                .put("message", "hello connected").build())
        id++

    }
    private fun setConnectionReceive() {
        connection?.receive(
            "showToast"
        ) { params, _ ->
            run {
                if (params.getString("action") == "show") {
                    interfaceFlipper?.getResult()
                }
            }
        }
    }
    override fun onDisconnect() {
        connection = null
    }

    override fun runInBackground(): Boolean = false

    private fun newRow(row: FlipperObject) {
        connection?.send("newData", row)
    }

    fun update(toString: String) {
        newRow(FlipperObject.Builder()
            .put("text", id)
            .put("message", toString).build())
        id++
    }

    fun setInterface(interfaceFlipper: com.teen.flippercommunication.FlipperPlugin) {
        this.interfaceFlipper = interfaceFlipper
    }
}