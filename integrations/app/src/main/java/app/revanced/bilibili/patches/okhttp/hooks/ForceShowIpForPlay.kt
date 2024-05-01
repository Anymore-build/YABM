package app.revanced.bilibili.patches.okhttp.hooks

import app.revanced.bilibili.meta.Client
import app.revanced.bilibili.patches.okhttp.BaseFakeClientGrpcHook
import app.revanced.bilibili.patches.protobuf.MossPatch
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Utils

object ForceShowIpForPlay : BaseFakeClientGrpcHook() {
    override val fakeToClient: Client
        get() = Client.PINK

    override fun shouldHookBefore(url: String, headers: Array<String>): Boolean {
        return Settings.FORCE_SHOW_IP.boolean && Utils.isPlay()
                && MossPatch.fakeToPinkForIPApis.any { url.endsWith(it) }
    }
}