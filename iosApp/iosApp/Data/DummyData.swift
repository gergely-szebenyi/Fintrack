import Shared

// TODO: Use MockAPI models
let dummyTransactions: [Transaction] = [
    Transaction(id: "1", name: "Morning Coffee",   date: "Feb 19, 2026", amount: -5.50,    category: "Coffee",    icon: "☕"),
    Transaction(id: "2", name: "Salary Payment",   date: "Feb 18, 2026", amount: +3500.00, category: "Income",    icon: "📈"),
    Transaction(id: "3", name: "Grocery Shopping", date: "Feb 17, 2026", amount: -125.80,  category: "Food",      icon: "🍽️"),
    Transaction(id: "4", name: "Gas Station",      date: "Feb 16, 2026", amount: -45.00,   category: "Transport", icon: "🚗"),
    Transaction(id: "5", name: "New Shoes",        date: "Feb 15, 2026", amount: -89.99,   category: "Shopping",  icon: "🛍️"),
]

let dummyCategories: [CategorySpend] = [
    CategorySpend(name: "Food & Dining", amount: 325.80, percentage: 35, colorToken: 0xFF3D6BF8),
    CategorySpend(name: "Transport",     amount: 145.00, percentage: 16, colorToken: 0xFF9B59B6),
    CategorySpend(name: "Shopping",      amount: 289.99, percentage: 31, colorToken: 0xFFE91E8C),
    CategorySpend(name: "Coffee",        amount:  45.50, percentage:  5, colorToken: 0xFFF39C12),
    CategorySpend(name: "Health",        amount: 120.00, percentage: 13, colorToken: 0xFF2ECC71),
]
