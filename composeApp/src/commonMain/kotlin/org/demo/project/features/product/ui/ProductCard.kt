package org.demo.project.features.product.ui


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.demo.project.features.product.domain.Products

import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
//import models.Products
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.book_error_2
import org.demo.project.core.presentation.PulseAnimation
import org.jetbrains.compose.resources.painterResource


@Composable
fun ProductCard(product: Products) {
    ProductItem(product.title, product.description, product.price.toString(), product.discountPercentage.toString(),product.thumbnail)

}


@Composable
fun ProductItem(name: String, description: String, price: String, discount: String, image: String) {
    println(image)
    val customCardElevation = CardDefaults.cardElevation(
        defaultElevation = 8.dp,
        pressedElevation = 2.dp,
        focusedElevation = 4.dp
    )
    Card(
        modifier = Modifier.padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = customCardElevation,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.height(390.dp).padding(10.dp)) {
            var imageLoadResult by remember {
                mutableStateOf<Result<Painter>?>(null)
            }
            val painter = rememberAsyncImagePainter(
                model = image,
                onSuccess = {
                    imageLoadResult =
                        if (it.painter.intrinsicSize.width > 1 && it.painter.intrinsicSize.height > 1) {
                            Result.success(it.painter)
                        } else {
                            Result.failure(Exception("Invalid image size"))
                        }
                },
                onError = {
                    it.result.throwable.printStackTrace()
                    imageLoadResult = Result.failure(it.result.throwable)
                }
            )
            val painterState by painter.state.collectAsStateWithLifecycle()
            val transition by animateFloatAsState(
                targetValue = if(painterState is AsyncImagePainter.State.Success) {
                    1f
                } else {
                    0f
                },
                animationSpec = tween(durationMillis = 800)
            )
            when (val result = imageLoadResult) {
                null -> PulseAnimation(
                    modifier = Modifier.size(60.dp)
                )
                else -> {
                    Image(
                        painter = if (result.isSuccess) painter else {
                            painterResource(Res.drawable.book_error_2)
                        },
                        contentDescription = "book.title",
                        contentScale = if (result.isSuccess) {
                            ContentScale.Crop
                        } else {
                            ContentScale.Fit
                        },
                        modifier = Modifier
                            .aspectRatio(
                                ratio = 0.65f,
                                matchHeightConstraintsFirst = true
                            )
                            .graphicsLayer {
                                rotationX = (1f - transition) * 30f
                                val scale = 0.8f + (0.2f * transition)
                                scaleX = scale
                                scaleY = scale
                            }
                    )
                }
            }
//            KamelImage(
//                { asyncPainterResource(image) },
//                contentDescription = null,
//                modifier = Modifier.height(150.dp).aspectRatio(1.0f), //.clip(MaterialTheme.shapes.medium),
//                contentScale = ContentScale.Crop
//            )
            Spacer(modifier = Modifier.height(16.dp))
            Column {
                Text(
                    modifier = Modifier.padding(2.dp),
                    text = name,
                    maxLines = 3,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    modifier = Modifier.padding(2.dp),
                    text = description,
                    fontWeight = FontWeight.Normal,
                    maxLines = 4,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier.padding(2.dp),
                    text = "$$price",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodySmall,
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    modifier = Modifier.padding(2.dp),
                    text = "$discount% discount",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(red=0.1f, green = 0.8f, blue = 0.0f)
                )
            }
        }
    }
}