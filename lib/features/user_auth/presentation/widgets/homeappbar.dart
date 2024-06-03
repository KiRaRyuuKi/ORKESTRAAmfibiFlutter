import 'package:flutter/material.dart';
import 'package:ionicons/ionicons.dart';
import 'package:Amfibi_App/features/shared/constants.dart';
import 'package:Amfibi_App/features/user_auth/presentation/pages/Message/message_page.dart'; // Add this import
import 'package:Amfibi_App/features/user_auth/presentation/pages/notifications/notification_screen.dart'; // Add this import

class HomeAppBar extends StatelessWidget {
  const HomeAppBar({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: [
        IconButton(
          onPressed: () {},
          style: IconButton.styleFrom(
            backgroundColor: kcontentColor,
            padding: const EdgeInsets.all(15),
          ),
          iconSize: 30,
          icon: const Icon(Ionicons.grid_outline),
        ),
        Row(
          children: [
            IconButton(
              onPressed: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => const MessagePage()),
                );
              },
              style: IconButton.styleFrom(
                backgroundColor: kcontentColor,
                padding: const EdgeInsets.all(15),
              ),
              iconSize: 30,
              icon: const Icon(Ionicons.mail_outline),
            ),
            IconButton(
              onPressed: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => NotificationScreen()),
                );
              },
              style: IconButton.styleFrom(
                backgroundColor: kcontentColor,
                padding: const EdgeInsets.all(15),
              ),
              iconSize: 30,
              icon: const Icon(Ionicons.notifications_outline),
            ),
          ],
        ),
      ],
    );
  }
}
