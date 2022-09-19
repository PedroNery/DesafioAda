package com.projeto.designsystem.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.core.content.withStyledAttributes
import androidx.core.widget.addTextChangedListener
import com.projeto.common.base.extensions.load
import com.projeto.designsystem.R
import com.projeto.designsystem.databinding.SpetacleCardBinding
import com.projeto.designsystem.databinding.SpetacleEditTextBinding

class SpetacleEditText @JvmOverloads constructor(
    context: Context,
    private val attributeSet: AttributeSet? = null
) : LinearLayout(context, attributeSet) {

    val text: String
        get() = binding.etSearchMovie.text.toString()

    var onTextChanged: (String) -> Unit = {}

    private val binding: SpetacleEditTextBinding by lazy {
        SpetacleEditTextBinding.inflate(
            LayoutInflater.from(context), this, true
        )
    }

    init {
        binding.etSearchMovie.addTextChangedListener {
            onTextChanged(it.toString())
        }
        parseAttrs(context, attributeSet)
    }

    private fun parseAttrs(context: Context, attrs: AttributeSet?) {
        context.withStyledAttributes(attrs, R.styleable.SpetacleEditText) {
            binding.tilSearchMovie.hint = getString(R.styleable.SpetacleEditText_hint)
        }
    }

}