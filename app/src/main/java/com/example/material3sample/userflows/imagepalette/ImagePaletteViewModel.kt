package com.example.material3sample.userflows.imagepalette

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.example.material3sample.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.InputStream

class ImagePaletteViewModel: ViewModel() {
    companion object {
        private val imageUrl =
            "https://assets.wfcdn.com/im/56648013/resize-h445%5Ecompr-r85/2430/243039725/Golden+Color+Sunflower+On+Canvas+by+Mycola+Photograph.jpg"
    }

    data class ViewState(
        val imageBitmap: ImageBitmap? = null,
        val colorPalette: Palette? = null,
        val imageUrl: String = ImagePaletteViewModel.imageUrl
    )

    private val _viewState = MutableStateFlow(ViewState())
    val viewState = _viewState.asStateFlow()

    val imageUrlFlow = viewState.map { it.imageUrl }
    val palette = viewState.map { it.colorPalette }.filterNotNull()

    fun setImageUri(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
//            context.contentResolver.openInputStream(
//                android.net.Uri.parse(imageUrl)
//            )?
            val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.sunflower)
            _viewState.update {currentState ->
                currentState.copy(
                    imageBitmap = bitmap.asImageBitmap(),
                    colorPalette = Palette.from(bitmap).generate()
                )
            }
//                val bitmap = BitmapFactory.decodeStream(inputStream)
//                _viewState.update { currentState: ViewState ->
//                    currentState.copy(
//                        imageBitmap = bitmap.asImageBitmap(),
//                        colorPalette = Palette.from(bitmap).generate()
//                    )
//                }
//            }
        }
    }

}