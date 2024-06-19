import 'package:flutter/material.dart';
import 'package:flutter_rating_bar/flutter_rating_bar.dart';
import 'package:orkestra_amfibi_flutter/app_theme.dart';
import 'package:orkestra_amfibi_flutter/models/cars_list_data.dart';

class CarsListView extends StatefulWidget {
  final AnimationController? animationController;
  final Animation<double>? animation;

  const CarsListView({
    Key? key,
    this.animationController,
    this.animation,
  }) : super(key: key);

  @override
  _CarsListViewState createState() => _CarsListViewState();
}

class _CarsListViewState extends State<CarsListView>
    with TickerProviderStateMixin {
  AnimationController? animationController;
  List<CarsListData> carsListData = CarsListData.tabIconsList;

  @override
  void initState() {
    animationController = AnimationController(
      duration: const Duration(milliseconds: 1000),
      vsync: this,
    );
    super.initState();
    animationController
        ?.forward(); // Start the animation when the widget is first loaded
  }

  @override
  void dispose() {
    animationController?.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return AnimatedBuilder(
      animation: widget.animationController!,
      builder: (BuildContext context, Widget? child) {
        return FadeTransition(
          opacity: widget.animation!,
          child: Transform(
            transform: Matrix4.translationValues(
              0.0,
              100 * (1.0 - widget.animation!.value),
              0.0,
            ),
            child: SingleChildScrollView(
              scrollDirection: Axis.vertical,
              child: Padding(
                padding: const EdgeInsets.symmetric(horizontal: 10.0),
                child: Column(
                  children: _buildCarViews(),
                ),
              ),
            ),
          ),
        );
      },
    );
  }

  List<Widget> _buildCarViews() {
    List<Widget> widgets = [];
    for (int i = 0; i < carsListData.length; i++) {
      final Animation<double> animation =
          Tween<double>(begin: 0.0, end: 1.0).animate(
        CurvedAnimation(
          parent: animationController!,
          curve: Interval((1 / carsListData.length) * i, 1.0,
              curve: Curves.fastOutSlowIn),
        ),
      );

      widgets.add(
        AnimatedBuilder(
          animation: animationController!,
          builder: (BuildContext context, Widget? child) {
            return FadeTransition(
              opacity: animation,
              child: Transform(
                transform: Matrix4.translationValues(
                  0.0,
                  100 * (1.0 - animation.value),
                  0.0,
                ),
                child: Padding(
                  padding: const EdgeInsets.only(left: 16, right: 16, top: 22),
                  child: Container(
                    width: double.infinity,
                    decoration: BoxDecoration(
                      boxShadow: <BoxShadow>[
                        BoxShadow(
                          color: AppTheme.grey.withOpacity(0.5),
                          offset: const Offset(1.8, 2.5),
                          blurRadius: 5.0,
                        ),
                      ],
                      gradient: LinearGradient(
                        colors: <Color>[
                          AppTheme.white,
                          AppTheme.background,
                        ],
                        begin: Alignment.topLeft,
                        end: Alignment.bottomRight,
                      ),
                      borderRadius: const BorderRadius.all(
                        Radius.circular(12.0),
                      ),
                    ),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: <Widget>[
                        ClipRRect(
                          borderRadius: BorderRadius.only(
                            topLeft: Radius.circular(12.0),
                            topRight: Radius.circular(12.0),
                          ),
                          child: Image.asset(
                            carsListData[i].imagePath,
                            fit: BoxFit.cover,
                            width: double.infinity,
                            height: 200,
                          ),
                        ),
                        Padding(
                          padding: const EdgeInsets.all(16.0),
                          child: Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: <Widget>[
                              Text(
                                '${carsListData[i].brand}, ${carsListData[i].name} - (${carsListData[i].year})',
                                textAlign: TextAlign.left,
                                style: TextStyle(
                                  fontFamily: AppTheme.fontName,
                                  fontWeight: FontWeight.bold,
                                  fontSize: 18,
                                  letterSpacing: 0.2,
                                  color: AppTheme.darkText,
                                ),
                              ),
                              SizedBox(height: 12),
                              Row(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                  Container(
                                    width: 80,
                                    child: Row(
                                      children: [
                                        Icon(
                                          Icons.location_on,
                                          color: AppTheme.grey.withOpacity(0.8),
                                          size: 16,
                                        ),
                                        SizedBox(width: 4),
                                        Text(
                                          'Location',
                                          style: TextStyle(
                                            fontSize: 14,
                                            color:
                                                AppTheme.grey.withOpacity(0.8),
                                          ),
                                        ),
                                      ],
                                    ),
                                  ),
                                  SizedBox(width: 8),
                                  Expanded(
                                    child: Row(
                                      crossAxisAlignment:
                                          CrossAxisAlignment.start,
                                      children: [
                                        Text(
                                          ':',
                                          style: TextStyle(
                                            fontSize: 14,
                                            color:
                                                AppTheme.grey.withOpacity(0.8),
                                          ),
                                        ),
                                        SizedBox(width: 4),
                                        Expanded(
                                          child: Text(
                                            '${carsListData[i].location}',
                                            style: TextStyle(
                                              fontSize: 14,
                                              color: AppTheme.grey
                                                  .withOpacity(0.8),
                                            ),
                                          ),
                                        ),
                                      ],
                                    ),
                                  ),
                                ],
                              ),
                              SizedBox(height: 2),
                              Row(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                  Container(
                                    width: 80,
                                    child: Row(
                                      children: [
                                        Icon(
                                          Icons.info,
                                          color: AppTheme.grey.withOpacity(0.8),
                                          size: 16,
                                        ),
                                        SizedBox(width: 4),
                                        Text(
                                          'Status',
                                          style: TextStyle(
                                            fontSize: 14,
                                            color:
                                                AppTheme.grey.withOpacity(0.8),
                                          ),
                                        ),
                                      ],
                                    ),
                                  ),
                                  SizedBox(width: 8),
                                  Expanded(
                                    child: Row(
                                      crossAxisAlignment:
                                          CrossAxisAlignment.start,
                                      children: [
                                        Text(
                                          ':',
                                          style: TextStyle(
                                            fontSize: 14,
                                            color:
                                                AppTheme.grey.withOpacity(0.8),
                                          ),
                                        ),
                                        SizedBox(width: 4),
                                        Expanded(
                                          child: Text(
                                            '${carsListData[i].status}',
                                            style: TextStyle(
                                              fontSize: 14,
                                              color: AppTheme.grey
                                                  .withOpacity(0.8),
                                            ),
                                          ),
                                        ),
                                      ],
                                    ),
                                  ),
                                ],
                              ),
                              SizedBox(height: 12),
                              Row(
                                mainAxisAlignment:
                                    MainAxisAlignment.spaceBetween,
                                children: <Widget>[
                                  Text(
                                    '\$${carsListData[i].price}',
                                    style: TextStyle(
                                      fontSize: 16,
                                      fontWeight: FontWeight.bold,
                                      color: AppTheme.darkText,
                                    ),
                                  ),
                                  Row(
                                    children: <Widget>[
                                      RatingBar.builder(
                                        initialRating: carsListData[i].rating,
                                        minRating: 1,
                                        direction: Axis.horizontal,
                                        allowHalfRating: true,
                                        itemCount: 5,
                                        itemSize: 20.0,
                                        itemPadding: EdgeInsets.zero,
                                        itemBuilder: (context, _) => Icon(
                                          Icons.star_rounded,
                                          color: Colors.amber,
                                        ),
                                        onRatingUpdate: (rating) {
                                          print(rating);
                                        },
                                      ),
                                      SizedBox(width: 4),
                                      Text(
                                        '(${carsListData[i].rating})',
                                        style: TextStyle(
                                          fontSize: 14,
                                          fontWeight: FontWeight.bold,
                                          color: AppTheme.darkText,
                                        ),
                                      ),
                                    ],
                                  ),
                                ],
                              ),
                            ],
                          ),
                        ),
                      ],
                    ),
                  ),
                ),
              ),
            );
          },
        ),
      );
    }
    return widgets;
  }
}
