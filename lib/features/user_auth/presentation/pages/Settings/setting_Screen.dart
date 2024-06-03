import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:Amfibi_App/features/helper/helper_function.dart';
import 'package:Amfibi_App/features/user_auth/presentation/pages/login/login_page.dart';
import 'package:Amfibi_App/features/user_auth/presentation/pages/profile/profile_page.dart'; 


class SettingsScreen extends StatelessWidget {
  
  final String email;
  final String userName;

  SettingsScreen({required this.email, required this.userName});


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Pengaturan'),
      ),
      body: ListView(
        children: [
          ListTile(
            leading: Icon(Icons.person),
            title: Text('Profile'),
            onTap: () {
              Navigator.pushReplacement(
                context,
                MaterialPageRoute(builder: (context) => ProfilePage(email: email, userName: userName)),
              );
            },
          ),
          ListTile(
            leading: Icon(Icons.security),
            title: Text('Keamanan'),
            onTap: () {
              // Aksi untuk halaman keamanan
            },
          ),
          ListTile(
            leading: Icon(Icons.privacy_tip),
            title: Text('Privasi'),
            onTap: () {
              // Aksi untuk halaman privasi
            },
          ),
          ListTile(
            leading: Icon(Icons.logout),
            title: Text('Log out'),
            onTap: () {
              showDialog(
                context: context,
                builder: (BuildContext context) {
                  return AlertDialog(
                    title: Text('Konfirmasi Log out'),
                    content: Text('Apakah Anda yakin ingin log out?'),
                    actions: [
                      TextButton(
                        onPressed: () {
                          Navigator.of(context).pop();
                        },
                        child: Text('Batal'),
                      ),
                      TextButton(
                        onPressed: () async {
                          // Log out user and navigate to login screen
                          await FirebaseAuth.instance.signOut();
                          SharedPreferences prefs =
                              await SharedPreferences.getInstance();
                          await prefs.clear();
                          Navigator.of(context).pop();
                          Navigator.pushReplacement(
                            context,
                            MaterialPageRoute(
                                builder: (context) => LoginPage()),
                          );
                        },
                        child: Text('Log out'),
                      ),
                    ],
                  );
                },
              );
            },
          ),
        ],
      ),
    );
  }
}