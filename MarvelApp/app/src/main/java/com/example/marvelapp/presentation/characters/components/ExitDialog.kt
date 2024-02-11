package com.example.marvelapp.presentation.characters.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.marvelapp.ui.theme.LogOutDialogColor

@Composable
fun ExitDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    if (show) {
        Dialog(
            onDismissRequest = { onDismiss() },
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            ConstraintLayout(modifier = Modifier
                .fillMaxWidth()
                .height(175.dp)
                .background(LogOutDialogColor)) {
                val (title, subtitle, noButton, yesButton) = createRefs()

                Text("Salir de la aplicación.", fontSize = 20.sp, color = Color.White, modifier = Modifier.constrainAs(title) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                })
                Text("¿Estás seguro?", fontSize = 17.sp, color = Color.White, modifier = Modifier.constrainAs(subtitle) {
                    top.linkTo(title.bottom, margin = 6.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                })
                TextButton(onClick = { onDismiss() }, modifier = Modifier.constrainAs(noButton) {
                    end.linkTo(yesButton.start, margin = 6.dp)
                    bottom.linkTo(parent.bottom)
                }) {
                    Text(text = "NO", fontSize = 18.sp)
                }
                TextButton(onClick = { onConfirm() }, modifier = Modifier.constrainAs(yesButton) {
                    end.linkTo(parent.end, margin = 8.dp)
                    bottom.linkTo(parent.bottom)
                }) {
                    Text(text = "SÍ", fontSize = 18.sp)
                }
            }
        }
    }
}