import 'package:orkestra_amfibi_flutter/pages/ui_menu/cars_list_view.dart';
import 'package:orkestra_amfibi_flutter/pages/ui_view/footer_view.dart';
import 'package:orkestra_amfibi_flutter/pages/ui_view/carousel_view.dart';
import 'package:orkestra_amfibi_flutter/pages/ui_view/title_view.dart';
import 'package:orkestra_amfibi_flutter/app_theme.dart';
import 'package:orkestra_amfibi_flutter/pages/ui_menu/categorys_list_view.dart';
import 'package:flutter/material.dart';

class DashboardScreen extends StatefulWidget {
  const DashboardScreen({Key? key, this.animationController}) : super(key: key);

  final AnimationController? animationController;
  @override
  _DashboardScreenState createState() => _DashboardScreenState();
}

class _DashboardScreenState extends State<DashboardScreen>
    with TickerProviderStateMixin {
  Animation<double>? topBarAnimation;

  List<Widget> listViews = <Widget>[];
  final ScrollController scrollController = ScrollController();
  double topBarOpacity = 0.0;

  @override
  void initState() {
    topBarAnimation = Tween<double>(begin: 0.0, end: 1.0).animate(
        CurvedAnimation(
            parent: widget.animationController!,
            curve: Interval(0, 0.5, curve: Curves.fastOutSlowIn)));
    addAllListData();

    scrollController.addListener(() {
      if (scrollController.offset >= 24) {
        if (topBarOpacity != 1.0) {
          setState(() {
            topBarOpacity = 1.0;
          });
        }
      } else if (scrollController.offset <= 24 &&
          scrollController.offset >= 0) {
        if (topBarOpacity != scrollController.offset / 24) {
          setState(() {
            topBarOpacity = scrollController.offset / 24;
          });
        }
      } else if (scrollController.offset <= 0) {
        if (topBarOpacity != 0.0) {
          setState(() {
            topBarOpacity = 0.0;
          });
        }
      }
    });
    super.initState();
  }

  void addAllListData() {
    const int count = 9;

    Animation<double> animation = Tween<double>(begin: 0.0, end: 1.0).animate(
      CurvedAnimation(
        parent: widget.animationController!,
        curve: Interval((1 / count) * 2, 1.0, curve: Curves.fastOutSlowIn),
      ),
    );

    listViews.add(
      AnimatedBuilder(
        animation: animation,
        builder: (BuildContext context, Widget? child) {
          return FadeTransition(
            opacity: animation,
            child: Padding(
              padding: const EdgeInsets.only(
                  top: 18.0, left: 28.0, bottom: 18.0, right: 28.0),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        'Muhammad Ilham',
                        style: TextStyle(
                          fontFamily: 'AppTheme.fontName',
                          fontWeight: FontWeight.w700,
                          fontSize: 18,
                          color: AppTheme.dark_grey,
                        ),
                      ),
                      SizedBox(height: 4),
                      Text(
                        'm.ilham.v.28.07.2003@gmail.com',
                        style: TextStyle(
                          fontFamily: 'AppTheme.fontName',
                          fontWeight: FontWeight.w400,
                          fontSize: 14,
                          color: Colors.grey,
                        ),
                      ),
                    ],
                  ),
                  Icon(
                    Icons.mail_rounded,
                    size: 35,
                    color: AppTheme.dark_grey,
                  ),
                ],
              ),
            ),
          );
        },
      ),
    );

    listViews.add(
      CarouselView(
        animation: Tween<double>(begin: 0.0, end: 1.0).animate(CurvedAnimation(
            parent: widget.animationController!,
            curve:
                Interval((1 / count) * 1, 1.0, curve: Curves.fastOutSlowIn))),
        animationController: widget.animationController!,
      ),
    );

    listViews.add(
      TitleView(
        titleTxt: 'Brands',
        subTxt: 'View All',
        animation: Tween<double>(begin: 0.0, end: 1.0).animate(CurvedAnimation(
            parent: widget.animationController!,
            curve:
                Interval((1 / count) * 2, 1.0, curve: Curves.fastOutSlowIn))),
        animationController: widget.animationController!,
      ),
    );

    listViews.add(
      CategorysListView(
        mainScreenAnimation: Tween<double>(begin: 0.0, end: 1.0).animate(
            CurvedAnimation(
                parent: widget.animationController!,
                curve: Interval((1 / count) * 3, 1.0,
                    curve: Curves.fastOutSlowIn))),
        mainScreenAnimationController: widget.animationController,
      ),
    );

    listViews.add(
      TitleView(
        titleTxt: 'Cars',
        subTxt: 'View All',
        animation: Tween<double>(begin: 0.0, end: 1.0).animate(CurvedAnimation(
            parent: widget.animationController!,
            curve:
                Interval((1 / count) * 4, 1.0, curve: Curves.fastOutSlowIn))),
        animationController: widget.animationController!,
      ),
    );

    listViews.add(
      CarsListView(
        animation: Tween<double>(begin: 0.0, end: 1.0).animate(CurvedAnimation(
            parent: widget.animationController!,
            curve:
                Interval((1 / count) * 5, 1.0, curve: Curves.fastOutSlowIn))),
        animationController: widget.animationController!,
      ),
    );

    listViews.add(
      FooterView(
          animation: Tween<double>(begin: 0.0, end: 1.0).animate(
              CurvedAnimation(
                  parent: widget.animationController!,
                  curve: Interval((1 / count) * 8, 1.0,
                      curve: Curves.fastOutSlowIn))),
          animationController: widget.animationController!),
    );
  }

  Future<bool> getData() async {
    await Future<dynamic>.delayed(const Duration(milliseconds: 50));
    return true;
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      color: AppTheme.background,
      child: Scaffold(
        backgroundColor: Colors.transparent,
        body: Stack(
          children: <Widget>[
            getMainListViewUI(),
            getAppBarUI(),
            SizedBox(
              height: MediaQuery.of(context).padding.bottom,
            )
          ],
        ),
      ),
    );
  }

  Widget getMainListViewUI() {
    return FutureBuilder<bool>(
      future: getData(),
      builder: (BuildContext context, AsyncSnapshot<bool> snapshot) {
        if (!snapshot.hasData) {
          return const SizedBox();
        } else {
          return ListView.builder(
            controller: scrollController,
            padding: EdgeInsets.only(
              top: AppBar().preferredSize.height +
                  MediaQuery.of(context).padding.top +
                  24,
              bottom: 62 + MediaQuery.of(context).padding.bottom,
            ),
            itemCount: listViews.length,
            scrollDirection: Axis.vertical,
            itemBuilder: (BuildContext context, int index) {
              widget.animationController?.forward();
              return listViews[index];
            },
          );
        }
      },
    );
  }

  Widget getAppBarUI() {
    return Column(
      children: <Widget>[
        AnimatedBuilder(
          animation: widget.animationController!,
          builder: (BuildContext context, Widget? child) {
            return FadeTransition(
              opacity: topBarAnimation!,
              child: Transform(
                transform: Matrix4.translationValues(
                    0.0, 30 * (1.0 - topBarAnimation!.value), 0.0),
                child: Container(
                  decoration: BoxDecoration(
                    color: AppTheme.white.withOpacity(topBarOpacity),
                    boxShadow: <BoxShadow>[
                      BoxShadow(
                          color: AppTheme.grey.withOpacity(0.4 * topBarOpacity),
                          offset: const Offset(1.1, 1.1),
                          blurRadius: 10.0),
                    ],
                  ),
                  child: Column(
                    children: <Widget>[
                      SizedBox(
                        height: MediaQuery.of(context).padding.top,
                      ),
                      Padding(
                        padding: EdgeInsets.only(
                            left: 16,
                            right: 16,
                            top: 16 - 8.0 * topBarOpacity,
                            bottom: 12 - 8.0 * topBarOpacity),
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: <Widget>[
                            Expanded(
                              child: Padding(
                                padding: const EdgeInsets.all(8.0),
                                child: Row(
                                  children: [
                                    Icon(
                                      Icons.menu_rounded,
                                      size: 28 + 6 - 6 * topBarOpacity,
                                      color: AppTheme.dark_grey,
                                    ),
                                    Spacer(),
                                    Icon(
                                      Icons.notifications,
                                      size: 28 + 6 - 6 * topBarOpacity,
                                      color: AppTheme.dark_grey,
                                    ),
                                    SizedBox(width: 22),
                                    Container(
                                      width: 40,
                                      height: 40,
                                      decoration: BoxDecoration(
                                        shape: BoxShape.rectangle,
                                        borderRadius:
                                            BorderRadius.circular(8.0),
                                        image: DecorationImage(
                                          image:
                                              AssetImage('assets/profile.png'),
                                          fit: BoxFit.cover,
                                        ),
                                      ),
                                    ),
                                  ],
                                ),
                              ),
                            ),
                          ],
                        ),
                      )
                    ],
                  ),
                ),
              ),
            );
          },
        )
      ],
    );
  }
}
