import 'package:orkestra_amfibi_flutter/main.dart';
import 'package:flutter/material.dart';
import '../../app_theme.dart';

class FooterView extends StatelessWidget {
  final AnimationController animationController;
  final Animation<double> animation;

  const FooterView({
    Key? key,
    required this.animationController,
    required this.animation,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return AnimatedBuilder(
      animation: animationController,
      builder: (BuildContext context, Widget? child) {
        return FadeTransition(
          opacity: animation,
          child: Transform(
            transform: Matrix4.translationValues(
              0.0,
              30 * (1.0 - animation.value),
              0.0,
            ),
            child: Column(
              children: <Widget>[
                SizedBox(height: 10),
                Padding(
                  padding: const EdgeInsets.only(
                      left: 24, right: 24, top: 0, bottom: 24),
                  child: Stack(
                    clipBehavior: Clip.none,
                    children: <Widget>[
                      Padding(
                        padding: const EdgeInsets.only(top: 16),
                        child: Container(
                          decoration: BoxDecoration(
                            color: HexColor("#D7E0F9"),
                            borderRadius: BorderRadius.circular(8.0),
                          ),
                          child: Padding(
                            padding: const EdgeInsets.fromLTRB(68, 12, 16, 12),
                            child: Text(
                              "Kami ingin memastikan pengalaman Anda dengan kami lebih baik lagi.",
                              textAlign: TextAlign.left,
                              style: TextStyle(
                                fontFamily: AppTheme.fontName,
                                fontWeight: FontWeight.w500,
                                fontSize: 14,
                                letterSpacing: 0.0,
                                color: AppTheme.nearlyDarkBlue.withOpacity(0.6),
                              ),
                            ),
                          ),
                        ),
                      ),
                      Positioned(
                        top: 20.5,
                        left: 10,
                        child: SizedBox(
                          width: 55,
                          height: 55,
                          child: Image.asset("assets/app_icon.png"),
                        ),
                      )
                    ],
                  ),
                ),
              ],
            ),
          ),
        );
      },
    );
  }
}
