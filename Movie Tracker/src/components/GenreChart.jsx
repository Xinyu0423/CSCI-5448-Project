import React from "react"

import { CanvasJSChart } from 'canvasjs-react-charts'

import { orderBy } from "lodash"

// Format genre data based on the specified key
const formatGenreData = (data, sort) => {
    const formattedData = []
    for (var key in data) {
        formattedData.push({
            y: data[key],
            label: key,
        })
    }
    if (sort === "alphabetical") {
        return formattedData
    }

    return orderBy(formattedData, 'y', 'desc')
}

// Genre Chart - Shows a stacked bar chart of all movie genres a user has seen and their counts
const GenreChart = ({ data, sort }) => {
    const options = {
        animationEnabled: true,
        theme: "light2",
        axisX: {
            title: "Genre",
            reversed: true,
            interval: 1,
        },
        axisY: {
            title: "Movie Count",
            includeZero: true,
        },
        data: [{
            type: "bar",
            dataPoints: formatGenreData(data, sort)
        }]
    }
    return (
        <CanvasJSChart options={options} />
    )
}

export default GenreChart