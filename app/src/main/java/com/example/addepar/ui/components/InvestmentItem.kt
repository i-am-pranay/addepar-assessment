package com.example.addepar.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.addepar.common.utils.InvestmentColorTheme
import com.example.addepar.domain.models.Investment

@Composable
fun InvestmentItem(investment: Investment) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f, label = "icon rotation"
    )


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(InvestmentColorTheme.backgound)
            .padding(5.dp)
    ) {
        Card(
            onClick = {
                expandedState = !expandedState
            },
            Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = InvestmentColorTheme.card,
            ),
        ) {
            Box {

                IconButton(
                    modifier = Modifier
                        .size(50.dp)
                        .alpha(0.2f)
                        .rotate(rotationState)
                        .align(Alignment.TopEnd),
                    onClick = {
                        expandedState = !expandedState
                    }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down Arrow"
                    )
                }
                Column(Modifier.padding(10.dp)) {
                    Text(text = investment.name, style = MaterialTheme.typography.labelLarge)
                    Text(
                        text = investment.ticker,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.outline
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "principal: ${investment.principal}",
                        style = MaterialTheme.typography.bodySmall,
                        color = InvestmentColorTheme.unknown_color
                    )
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "value: ${investment.value}",
                            style = MaterialTheme.typography.bodySmall,
                            color = investment.valColor.color
                        )

                        investment.icon?.let {
                            Image(
                                painter = painterResource(id = it),
                                contentDescription = "icon",
                                modifier = Modifier.size(10.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))





                    if (expandedState) {
                        Text(
                            text = investment.details,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                }


            }
        }
    }
}


@Preview
@Composable
fun InvestmentItemPreview() {
    InvestmentItem(investment = Investment.preview)
}