package com.example.weathercomposeneco.utils.resourceprovider

import android.content.Context
import javax.inject.Inject

class ResourceProviderImpl @Inject constructor(
    private val context: Context
) : ResourceProvider {

    override fun string(id: Int) = context.resources.getString(id)
}