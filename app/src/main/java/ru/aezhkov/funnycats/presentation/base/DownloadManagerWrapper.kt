package ru.aezhkov.funnycats.presentation.base

import android.app.DownloadManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.net.Uri
import android.os.Environment
import ru.aezhkov.funnycats.presentation.list.model.CatUiModel
import javax.inject.Inject


class DownloadManagerWrapper
@Inject constructor(
    private val context: Context
) {
    fun startDownload(model: CatUiModel): Long {
        val request = DownloadManager.Request(Uri.parse(model.url))
            .setTitle(model.id)
            .setDescription("Downloading")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, model.id)
            .setAllowedOverMetered(true)
            .setAllowedOverRoaming(true)
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)

        val downloadManager = context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager?
        return downloadManager!!.enqueue(request)
    }
}