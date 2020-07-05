package com.gijuno.paymeback
import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.ProfileSyncState.set
import android.system.Os.bind
import android.util.Log
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.core.graphics.drawable.DrawableCompat.inflate
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.threetenabp.AndroidThreeTen
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.CalendarMonth
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.MonthHeaderFooterBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import com.kizitonwose.calendarview.utils.next
import com.kizitonwose.calendarview.utils.previous
import com.kizitonwose.calendarview.utils.yearMonth
import kotlinx.android.synthetic.main.activity_calendar.*
import kotlinx.android.synthetic.main.calendar_day_layout.view.*
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.YearMonth
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.WeekFields
import java.util.*
import java.util.Date.from


data class Event(val id: String, val text: String, val date: LocalDate)



private var selectedDate: LocalDate? = null

class CalendarActivity : AppCompatActivity() {
    private val monthTitleFormatter = DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.KOREA)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        calendar_layout.clipToOutline=true





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


        mycalendarView.monthScrollListener = {
            if (mycalendarView.maxRowCount == 6) {
                this_year.text = it.yearMonth.year.toString()
                this_month.text = monthTitleFormatter.format(it.yearMonth)
            } else {
                // In week mode, we show the header a bit differently.
                // We show indices with dates from different months since
                // dates overflow and cells in one index can belong to different
                // months/years.
                val firstDate = it.weekDays.first().first().date
                val lastDate = it.weekDays.last().last().date
                if (firstDate.yearMonth == lastDate.yearMonth) {
                    this_year.text = firstDate.yearMonth.year.toString()
                    this_month.text = monthTitleFormatter.format(firstDate)
                } else {
                    this_month.text =
                        "${monthTitleFormatter.format(firstDate)} - ${monthTitleFormatter.format(
                            lastDate
                        )}"
                    if (firstDate.year == lastDate.year) {
                        this_year.text = firstDate.yearMonth.year.toString()
                    } else {
                        this_year.text = "${firstDate.yearMonth.year} - ${lastDate.yearMonth.year}"
                    }
                }
            }
        }

        prev_month.setOnClickListener {
            mycalendarView.findFirstVisibleMonth()?.let {
                mycalendarView.smoothScrollToMonth(it.yearMonth.previous)
            }
        }

        next_month.setOnClickListener {
            mycalendarView.findFirstVisibleMonth()?.let {
                mycalendarView.smoothScrollToMonth(it.yearMonth.next)
            }
        }

    }


    class DayViewContainer(view: View) : ViewContainer(view) {
        val textView = view.calendarDayText
    }
}