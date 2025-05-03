"use client";

import { useState } from "react";

// Dummy data for public holidays
const publicHolidays = [
  { date: "2024-01-01", name: "New Year's Day" },
  { date: "2024-04-09", name: "Araw ng Kagitingan" },
  { date: "2024-05-01", name: "Labor Day" },
  { date: "2024-06-12", name: "Independence Day" },
  { date: "2024-08-21", name: "Ninoy Aquino Day" },
  { date: "2024-08-26", name: "National Heroes Day" },
  { date: "2024-11-30", name: "Bonifacio Day" },
  { date: "2024-12-25", name: "Christmas Day" },
  { date: "2024-12-30", name: "Rizal Day" },
];

export default function Calendar() {
  const [selectedMonth, setSelectedMonth] = useState(new Date().getMonth());
  const [selectedYear, setSelectedYear] = useState(new Date().getFullYear());

  const months = [
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December",
  ];

  const getDaysInMonth = (month: number, year: number) => {
    return new Date(year, month + 1, 0).getDate();
  };

  const getFirstDayOfMonth = (month: number, year: number) => {
    return new Date(year, month, 1).getDay();
  };

  const isHoliday = (day: number) => {
    const date = new Date(selectedYear, selectedMonth, day);
    const dateString = date.toISOString().split("T")[0];
    return publicHolidays.some((holiday) => holiday.date === dateString);
  };

  const getHolidayName = (day: number) => {
    const date = new Date(selectedYear, selectedMonth, day);
    const dateString = date.toISOString().split("T")[0];
    const holiday = publicHolidays.find((h) => h.date === dateString);
    return holiday ? holiday.name : null;
  };

  const daysInMonth = getDaysInMonth(selectedMonth, selectedYear);
  const firstDay = getFirstDayOfMonth(selectedMonth, selectedYear);

  return (
    <div>
      <h1 className='text-3xl font-bold mb-8'>Public Holidays Calendar</h1>

      <div className='flex justify-between items-center mb-6'>
        <div className='flex space-x-4'>
          <select
            value={selectedMonth}
            onChange={(e) => setSelectedMonth(Number(e.target.value))}
            className='p-2 border rounded-md'
          >
            {months.map((month, index) => (
              <option key={month} value={index}>
                {month}
              </option>
            ))}
          </select>
          <select
            value={selectedYear}
            onChange={(e) => setSelectedYear(Number(e.target.value))}
            className='p-2 border rounded-md'
          >
            {[2023, 2024, 2025].map((year) => (
              <option key={year} value={year}>
                {year}
              </option>
            ))}
          </select>
        </div>
      </div>

      <div className='bg-white rounded-lg shadow overflow-hidden'>
        <div className='grid grid-cols-7 gap-1 p-4'>
          {["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"].map((day) => (
            <div key={day} className='text-center font-semibold py-2'>
              {day}
            </div>
          ))}

          {Array.from({ length: firstDay }).map((_, index) => (
            <div key={`empty-${index}`} className='h-20' />
          ))}

          {Array.from({ length: daysInMonth }).map((_, index) => {
            const day = index + 1;
            const isHolidayDay = isHoliday(day);
            const holidayName = getHolidayName(day);

            return (
              <div
                key={day}
                className={`h-20 p-2 border ${isHolidayDay ? "bg-red-50" : ""}`}
              >
                <div className='font-semibold'>{day}</div>
                {holidayName && (
                  <div className='text-xs text-red-600 mt-1'>{holidayName}</div>
                )}
              </div>
            );
          })}
        </div>
      </div>

      <div className='mt-8'>
        <h2 className='text-xl font-semibold mb-4'>Upcoming Holidays</h2>
        <ul className='space-y-2'>
          {publicHolidays
            .filter((holiday) => new Date(holiday.date) >= new Date())
            .sort(
              (a, b) => new Date(a.date).getTime() - new Date(b.date).getTime()
            )
            .map((holiday) => (
              <li
                key={holiday.date}
                className='flex justify-between items-center p-2 bg-white rounded shadow'
              >
                <span>{holiday.name}</span>
                <span className='text-gray-600'>{holiday.date}</span>
              </li>
            ))}
        </ul>
      </div>
    </div>
  );
}
