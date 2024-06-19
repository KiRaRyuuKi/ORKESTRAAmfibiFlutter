import 'package:flutter/material.dart';

class WelcomeView extends StatelessWidget {
  final AnimationController animationController;
  const WelcomeView({Key? key, required this.animationController})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    final _firstHalfAnimation =
        Tween<Offset>(begin: Offset(1, 0), end: Offset(0, 0)).animate(
      CurvedAnimation(
        parent: animationController,
        curve: Interval(
          0.6,
          0.8,
          curve: Curves.fastOutSlowIn,
        ),
      ),
    );
    final _secondHalfAnimation =
        Tween<Offset>(begin: Offset(0, 0), end: Offset(-1, 0)).animate(
      CurvedAnimation(
        parent: animationController,
        curve: Interval(
          0.8,
          1.0,
          curve: Curves.fastOutSlowIn,
        ),
      ),
    );

    final _welcomeFirstHalfAnimation =
        Tween<Offset>(begin: Offset(2, 0), end: Offset(0, 0))
            .animate(CurvedAnimation(
      parent: animationController,
      curve: Interval(
        0.6,
        0.8,
        curve: Curves.fastOutSlowIn,
      ),
    ));

    final _welcomeImageAnimation =
        Tween<Offset>(begin: Offset(4, 0), end: Offset(0, 0))
            .animate(CurvedAnimation(
      parent: animationController,
      curve: Interval(
        0.6,
        0.8,
        curve: Curves.fastOutSlowIn,
      ),
    ));
    return SlideTransition(
      position: _firstHalfAnimation,
      child: SlideTransition(
        position: _secondHalfAnimation,
        child: Padding(
          padding: const EdgeInsets.only(bottom: 100),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              SlideTransition(
                position: _welcomeImageAnimation,
                child: Container(
                  constraints: BoxConstraints(maxWidth: 450, maxHeight: 450),
                  child: Image.asset(
                    'assets/cartoon-people.png',
                    fit: BoxFit.contain,
                  ),
                ),
              ),
              SlideTransition(
                position: _welcomeFirstHalfAnimation,
                child: Text(
                  "Welcome to Amfibi!",
                  style: TextStyle(fontSize: 25.0, fontWeight: FontWeight.bold),
                ),
              ),
              Padding(
                padding:
                    EdgeInsets.only(left: 48, right: 48, top: 16, bottom: 16),
                child: Text(
                  "Selamat datang di Amfibi! Bersama-sama, kita akan menciptakan pengalaman yang tak terlupakan.",
                  textAlign: TextAlign.center,
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
