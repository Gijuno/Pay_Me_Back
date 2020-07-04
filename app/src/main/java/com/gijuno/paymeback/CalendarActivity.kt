package com.gijuno.paymeback

import android.app.Application
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.jakewharton.threetenabp.AndroidThreeTen
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import kotlinx.android.synthetic.main.activity_calendar.*
import kotlinx.android.synthetic.main.calendar_day_layout.view.*
import org.threeten.bp.YearMonth
import org.threeten.bp.temporal.WeekFields
import java.util.*


class CalendarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        val currentMonth = YearMonth.now()
        val firstMonth = currentMonth.minusMonths(10)
        val lastMonth = currentMonth.plusMonths(10)
        val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek
        mycalendarView.setup(firstMonth, lastMonth, firstDayOfWeek)
        mycalendarView.scrollToMonth(currentMonth)

        mycalendarView.dayBinder = object : DayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)
            override fun bind(container: DayViewContainer, day: CalendarDay) {
                container.textView.text = day.date.dayOfMonth.toString()
                if (day.owner == DayOwner.THIS_MONTH) {
                    container.textView.setTextColor(Color.parseColor("#777777"))
                } else {
                    container.textView.setTextColor(Color.parseColor("#D9D9D9"))
                }
            }
        }
    }

    class DayViewContainer(view: View) : ViewContainer(view) {
        val textView = view.calendarDayText
    }
}
