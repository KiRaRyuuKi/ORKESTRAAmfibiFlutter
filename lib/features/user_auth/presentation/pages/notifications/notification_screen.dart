import 'package:flutter/material.dart';
import 'package:Amfibi_App/features/user_auth/presentation/pages/model/notification.dart';

class NotificationScreen extends StatelessWidget {
  final List<NotificationModel> notifications = [
    NotificationModel(
      title: 'Special Discount!',
      message: 'Get 50% off on selected items.',
      time: '2 hours ago',
      isRead: false,
    ),
    NotificationModel(
      title: 'Order Shipped',
      message: 'Your order has been shipped and is on its way!',
      time: '5 hours ago',
      isRead: true,
    ),
    // Add more notifications here
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Notifications'),
      ),
      body: ListView.builder(
        itemCount: notifications.length,
        itemBuilder: (context, index) {
          final notification = notifications[index];
          return NotificationTile(notification: notification);
        },
      ),
    );
  }
}

class NotificationTile extends StatelessWidget {
  final NotificationModel notification;

  const NotificationTile({required this.notification});

  @override
  Widget build(BuildContext context) {
    return ListTile(
      leading: Icon(
        notification.isRead ? Icons.notifications : Icons.notifications_active,
        color: notification.isRead ? Colors.grey : Colors.blue,
      ),
      title: Text(
        notification.title,
        style: TextStyle(
          fontWeight: notification.isRead ? FontWeight.normal : FontWeight.bold,
        ),
      ),
      subtitle: Text(notification.message),
      trailing: Text(
        notification.time,
        style: TextStyle(
          color: Colors.grey,
          fontSize: 12,
        ),
      ),
      tileColor: notification.isRead ? Colors.white : Colors.blue.shade50,
      onTap: () {
        // Handle notification tap
      },
    );
  }
}
