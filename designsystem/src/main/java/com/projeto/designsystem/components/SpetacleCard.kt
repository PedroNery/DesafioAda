package com.projeto.designsystem.components

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import com.projeto.common.base.extensions.load
import com.projeto.designsystem.databinding.SpetacleCardBinding

class SpetacleCard @JvmOverloads constructor(
    context: Context,
    private val attributeSet: AttributeSet? = null
) : LinearLayout(context, attributeSet) {

    private val binding: SpetacleCardBinding by lazy {
        SpetacleCardBinding.inflate(
            LayoutInflater.from(context), this, true
        )
    }

    fun bind(title: String,
             description: String,
             @DrawableRes iconDrawable: Int) {
        binding.titleCard.text = title
        binding.descriptionCard.text = description
        binding.iconCard.load(iconDrawable)
    }

}