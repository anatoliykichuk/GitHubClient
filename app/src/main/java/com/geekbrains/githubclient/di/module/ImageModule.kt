package com.geekbrains.githubclient.di.module

import android.widget.ImageView
import com.geekbrains.githubclient.domain.GlideImageLoader
import com.geekbrains.githubclient.domain.common.IImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule {

    @Singleton
    @Provides
    fun imageLoader(): IImageLoader<ImageView> = GlideImageLoader()
}