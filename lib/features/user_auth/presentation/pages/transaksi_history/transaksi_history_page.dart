import 'package:flutter/material.dart';

class TransactionHistoryPage extends StatelessWidget {
  const TransactionHistoryPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Transaction History'),
      ),
      body: _buildTransactionList(),
    );
  }

  Widget _buildTransactionList() {
    return ListView.builder(
      padding: EdgeInsets.all(10),
      itemCount: 10, // Ganti dengan jumlah transaksi yang ingin ditampilkan
      itemBuilder: (BuildContext context, int index) {
        return Card(
          elevation: 4,
          margin: EdgeInsets.symmetric(vertical: 10),
          child: ListTile(
            leading: Icon(
              Icons.receipt_long,
              size: 40,
              color: Colors.blue,
            ),
            title: Text('Transaction ${index + 1}'),
            subtitle: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                    'Date: 2024-05-${index + 1}'), // Ganti dengan tanggal transaksi yang sebenarnya
                Text(
                    'Amount: \$${(index + 1) * 20}'), // Ganti dengan jumlah transaksi yang sebenarnya
                Text(
                    'Status: Completed'), // Ganti dengan status transaksi yang sebenarnya
              ],
            ),
            trailing: Icon(Icons.arrow_forward_ios),
            onTap: () {
              // Handle transaction tap, e.g., navigate to transaction details
            },
          ),
        );
      },
    );
  }
}
