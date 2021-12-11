package com.example.assignment4.utils.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.sigmadelta.calpose.Calpose
import be.sigmadelta.calpose.WEIGHT_7DAY_WEEK
import be.sigmadelta.calpose.model.CalposeActions
import be.sigmadelta.calpose.model.CalposeWidgets
import com.example.assignment4.R
import com.example.assignment4.ui.theme.Dm
import com.example.assignment4.view.reusables.HorizontalDivider
import com.example.testing.reusables.CircularIconButton
import org.threeten.bp.YearMonth
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

/**
 * Calender UI used in Screen-23.
 * **/

val CALENDAR_ROW_HEIGHT = 80.dp;

@Composable
fun AppCalendar(
    month: YearMonth,
    dates: List<Date>,
    actions: CalposeActions,
    modifier: Modifier = Modifier,
) {
    Calpose(
        modifier = modifier.background(MaterialTheme.colors.primary),
        month = month,
        actions = actions,
        widgets = CalposeWidgets(
            header = { month, todayMonth, actions ->

                val title = remember(month) {
                    val formatter = DateTimeFormatter.ofPattern("MMMM, yyyy")
                    month.format(formatter)
                }

                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.primary)
                        .padding(vertical = Dm.marginMedium),
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.body1
                    )
                }
            },
            headerDayRow = { headerDayList ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .background(MaterialTheme.colors.primary),
                ) {
                    headerDayList.forEach {
                        Text(
                            text = it.name[0].toString(),
                            modifier = modifier.weight(WEIGHT_7DAY_WEEK),
                            style = MaterialTheme.typography.body1
                        )
                    }
                }

                HorizontalDivider(height = 1.dp)
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.primary)
                        .height(CALENDAR_ROW_HEIGHT)
                )
            },
            day = { dayDate, todayDate ->

                // Date calculation examples
//                val isToday = dayDate == todayDate
//                val dayHasPassed = dayDate.day < todayDate.day
//                val isCurrentMonth = dayDate.month == todayDate.month

                val showDate = month == dayDate.month

                val showIcon = dates
                    .filter { it.date == dayDate.day }.isNotEmpty()

                CalendarDay(
                    text = dayDate.day.toString(),
                    displayDate = showDate,
                    modifier = Modifier
                        .weight(WEIGHT_7DAY_WEEK)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.body1,
                    displayBookIcon = showIcon,
                    displayImageIcon = showIcon
                )

            },
            priorMonthDay = { dayDate ->
                CalendarDay(
                    displayDate = false,
                    text = dayDate.day.toString(),
                    modifier = Modifier
                        .weight(WEIGHT_7DAY_WEEK)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.body1,
                )
            },
            headerContainer = { header ->
                header()
            },
        )
    )
}

@Composable
fun CalendarDay(
    text: String = "",
    modifier: Modifier = Modifier,
    displayBookIcon: Boolean = false,
    displayImageIcon: Boolean = false,
    displayDate: Boolean = true,
    style: TextStyle = TextStyle()
) {
    Column(
        modifier = modifier.height(CALENDAR_ROW_HEIGHT),
    ) {
        HorizontalDivider(height = 1.dp)

        Text(
            text = if (displayDate) text else "",
            textAlign = TextAlign.Center,
            style = style
        )

        if (displayBookIcon) {
            CircularIconButton(
                imageResource = R.drawable.book,
                buttonSize = 20.dp,
                iconPadding = 2.dp,
                iconTint = MaterialTheme.colors.primary,
                modifier = Modifier.padding(vertical = 2.dp)
            ) {}
        } else {
            Spacer(Modifier.height(24.dp))
        }

        if (displayImageIcon) {
            CircularIconButton(
                imageResource = R.drawable.image,
                buttonSize = 20.dp,
                iconPadding = 2.dp,
                iconTint = MaterialTheme.colors.primary
            ) {}
        } else {
            Spacer(Modifier.height(22.dp))
        }
    }
}

@Composable
private fun DatePlaceHolder(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        HorizontalDivider(height = 1.dp)
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.primary)
                .height(CALENDAR_ROW_HEIGHT)
        )
    }
}