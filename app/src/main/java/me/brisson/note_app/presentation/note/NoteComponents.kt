package me.brisson.note_app.presentation.note

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun TopComponent(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onEdit: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CustomButton(imageVector = Icons.Rounded.ArrowBack, onClick = onBack)
        CustomButton(imageVector = Icons.Rounded.Edit, onClick = onEdit)
    }
}

@Composable
private fun CustomButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surface)
            .clickable { onClick() }
    ) {
        Icon(
            modifier = Modifier.align(Alignment.Center),
            imageVector = imageVector,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}