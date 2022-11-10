package com.example.hope2

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.compose.foundation.layout.size as size1

@Composable
fun ProfilePage() {
    Card(elevation = 6.dp, modifier = Modifier
        .fillMaxSize()
        .padding(top = 100.dp, bottom = 100.dp, start = 16.dp, end = 16.dp)
        .border(
            width = 2.dp, color = Color.White, shape = RoundedCornerShape(30.dp)
        )) {
        // Content of Card i.e. Dog, and stats
        BoxWithConstraints() {
            val constraints = if(minWidth < 600.dp) {
                portraitConstraints(margin = 16.dp)
            } else {
                landscapeConstraints(margin = 16.dp)
            }
            ConstraintLayout(constraints) {

                Image(
                    ColorPainter(Color.Magenta),
                    contentDescription = "description of the image",
                    modifier = Modifier
                        .size1(100.dp)
                        .clip(CircleShape)
                        .border(width = 2.dp, Color.Blue, shape = CircleShape)
                        .layoutId("image"),
                        contentScale = ContentScale.Crop
                )

                Text(text = "Magenta Image", modifier = Modifier.layoutId("titleText"))
                Text(text = "At least it works", modifier = Modifier.layoutId("subtitleText"))

                Row(horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .layoutId("rowStats")) {
                    ProfileStats(count = "150", title = "Followers")
                    ProfileStats(count = "100", title = "Following")
                    ProfileStats(count = "100", title = "Posts")
                }

                Button(onClick = { /*TODO*/ }, modifier = Modifier.layoutId("followButton")) {
                    Text(text = "Follow User")
                }
                Button(onClick = { /*TODO*/ }, modifier = Modifier.layoutId("messageButton")) {
                    Text(text = "Direct Message")
                }

            }

        }
    } // : end card
}

@Composable
fun ProfileStats(count: String, title: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = count, fontWeight = FontWeight.Bold)
        Text(text = title)
    }
}

private fun portraitConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet() {
        val image = createRefFor("image")
        val titleText = createRefFor("titleText")
        val subtitleText = createRefFor("subtitleText")
        val rowStats = createRefFor("rowStats")
        val followButton = createRefFor("followButton")
        val messageButton = createRefFor("messageButton")
        val guideline = createGuidelineFromTop(0.1f)
        constrain(image) {
            top.linkTo(guideline)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(titleText) {
            top.linkTo(image.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(subtitleText) {
            top.linkTo(titleText.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(rowStats) {
            top.linkTo(subtitleText.bottom)
        }
        constrain(followButton) {
            top.linkTo(rowStats.bottom, margin = margin)
            start.linkTo(parent.start)
            end.linkTo(messageButton.start)
            width = Dimension.wrapContent
        }
        constrain(messageButton) {
            top.linkTo(rowStats.bottom, margin = margin)
            start.linkTo(followButton.end)
            end.linkTo(parent.end)
        }
    }
} // end portrait constraints

private fun landscapeConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet() {
        val image = createRefFor("image")
        val titleText = createRefFor("titleText")
        val subtitleText = createRefFor("subtitleText")
        val rowStats = createRefFor("rowStats")
        val followButton = createRefFor("followButton")
        val messageButton = createRefFor("messageButton")
        val guideline = createGuidelineFromTop(0.1f)

        constrain(image) {
            top.linkTo(parent.top, margin = margin)
            start.linkTo(parent.start, margin = margin)
        }
        constrain(titleText) {
            start.linkTo(image.start)
            top.linkTo(image.bottom)
        }
        constrain(subtitleText) {
            top.linkTo(titleText.bottom)
            start.linkTo(titleText.start)
            end.linkTo(titleText.end)
        }
        constrain(rowStats) {
            top.linkTo(image.top)
            start.linkTo(image.end, margin = margin)
            end.linkTo(parent.end)
        }
        constrain(followButton) {
            top.linkTo(rowStats.bottom, margin = 16.dp)
            start.linkTo(rowStats.start)
            end.linkTo(messageButton.start)
            bottom.linkTo(subtitleText.bottom)
            width = Dimension.wrapContent
        }
        constrain(messageButton) {
            top.linkTo(rowStats.bottom, margin = 16.dp)
            start.linkTo(followButton.end)
            end.linkTo(parent.end)
            bottom.linkTo(subtitleText.bottom)
            width = Dimension.wrapContent
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePagePreview() {
    ProfilePage()
}