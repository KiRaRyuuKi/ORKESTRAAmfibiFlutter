import 'package:flutter/material.dart';
import 'package:orkestra_amfibi_flutter/app_theme.dart';

class SplashView extends StatefulWidget {
  final AnimationController animationController;

  const SplashView({Key? key, required this.animationController})
      : super(key: key);

  @override
  _SplashViewState createState() => _SplashViewState();
}

class _SplashViewState extends State<SplashView> {
  @override
  Widget build(BuildContext context) {
    final _introductionanimation =
        Tween<Offset>(begin: Offset(0, 0), end: Offset(0.0, -1.0))
            .animate(CurvedAnimation(
      parent: widget.animationController,
      curve: Interval(
        0.0,
        0.2,
        curve: Curves.fastOutSlowIn,
      ),
    ));
    return SlideTransition(
      position: _introductionanimation,
      child: SingleChildScrollView(
        child: Column(
          children: [
            SizedBox(
              height: 145,
            ),
            SizedBox(
              width: MediaQuery.of(context).size.width,
              child: Image.asset(
                'assets/user-access.png',
                fit: BoxFit.cover,
              ),
            ),
            SizedBox(
              height: 22,
            ),
            Padding(
              padding: EdgeInsets.only(top: 8.0, bottom: 12.0),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Image.asset(
                    'assets/app_icon.png',
                    height: 38.0,
                    width: 38.0,
                  ),
                  Text(
                    "Amfibi",
                    style:
                        TextStyle(fontSize: 32.0, fontWeight: FontWeight.bold),
                  ),
                ],
              ),
            ),
            Padding(
              padding: EdgeInsets.only(left: 38, right: 38),
              child: Text(
                "Temukan mobil impian Anda dengan mudah melalui platform Amfibi yang menawarkan berbagai pilihan mobil untuk dijual dan disewakan.",
                textAlign: TextAlign.center,
              ),
            ),
            SizedBox(
              height: 52,
            ),
            Padding(
              padding: EdgeInsets.only(
                  bottom: MediaQuery.of(context).padding.bottom + 16),
              child: InkWell(
                onTap: () {
                  widget.animationController.animateTo(0.2);
                },
                child: Container(
                  height: 58,
                  padding: EdgeInsets.only(
                    left: 100.0,
                    right: 100.0,
                    top: 15,
                    bottom: 15,
                  ),
                  decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(12.0),
                    color: Color(0xff132137),
                    boxShadow: <BoxShadow>[
                      BoxShadow(
                          color: AppTheme.grey.withOpacity(0.2),
                          offset: Offset(1.1, 1.1),
                          blurRadius: 10.0),
                    ],
                  ),
                  child: Text(
                    "Let's begin",
                    style: TextStyle(
                      fontSize: 18,
                      color: Colors.white,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
