function sortProducts(){
    // Get the outer table
    const outerTable = document.querySelector('#outer-products-table');

// Get all the rows of the outer table
    const rows = outerTable.querySelectorAll('tr');

// Extract the data from the inner tables
    const data = [];
    rows.forEach(row => {
        const innerTable = row.querySelector('.inner-table');
        const cells = innerTable.querySelectorAll('td');
        const rowData = {
            price: Number(cells[0].textContent),
            expiredDate: new Date(cells[1].textContent),
            name: cells[2].textContent,
            quantity: Number(cells[3].textContent)
        };
        data.push(rowData);
    });

// Sort the data based on the selected criteria
    const sortCriteria = 'price'; // Replace with 'expiredDate', 'name', or 'quantity'
    data.sort((a, b) => a[sortCriteria] - b[sortCriteria]);

// Rearrange the rows of the outer table based on the sorted data
    rows.forEach((row, index) => {
        const innerTable = row.querySelector('.inner-table');
        const cells = innerTable.querySelectorAll('td');
        cells[0].textContent = data[index].price;
        cells[1].textContent = data[index].expiredDate;
        cells[2].textContent = data[index].name;
        cells[3].textContent = data[index].quantity;
        outerTable.appendChild(row);
    });
}