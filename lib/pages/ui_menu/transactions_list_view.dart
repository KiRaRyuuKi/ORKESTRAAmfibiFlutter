import 'package:flutter/material.dart';
import 'package:orkestra_amfibi_flutter/app_theme.dart';
import 'package:orkestra_amfibi_flutter/models/transactions_list_data.dart';

class TransactionsListView extends StatefulWidget {
  final AnimationController? animationController;
  final Animation<double>? animation;

  const TransactionsListView({
    Key? key,
    this.animationController,
    this.animation,
  }) : super(key: key);

  @override
  _TransactionsListViewState createState() => _TransactionsListViewState();
}

class _TransactionsListViewState extends State<TransactionsListView>
    with TickerProviderStateMixin {
  AnimationController? animationController;
  List<TransactionsListData> transactionsListData =
      TransactionsListData.tabIconsList;

  @override
  void initState() {
    animationController = AnimationController(
      duration: const Duration(milliseconds: 1000),
      vsync: this,
    );
    super.initState();
    animationController?.forward();
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
                  children: <Widget>[
                    ..._buildCarViews(),
                    SizedBox(height: 28),
                  ],
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
    for (int i = 0; i < transactionsListData.length; i++) {
      final Animation<double> animation =
          Tween<double>(begin: 0.0, end: 1.0).animate(
        CurvedAnimation(
          parent: animationController!,
          curve: Interval((1 / transactionsListData.length) * i, 1.0,
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
                          color: AppTheme.grey.withOpacity(0.4),
                          offset: Offset(1.1, 1.1),
                          blurRadius: 10.0,
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
                    child: Padding(
                      padding: const EdgeInsets.all(8.0),
                      child: Row(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: <Widget>[
                          ClipRRect(
                            borderRadius: BorderRadius.circular(8.0),
                            child: Image.asset(
                              transactionsListData[i].imagePath,
                              fit: BoxFit.cover,
                              width: 100,
                              height: 100,
                            ),
                          ),
                          SizedBox(width: 12),
                          Expanded(
                            child: Column(
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: <Widget>[
                                Text(
                                  '${transactionsListData[i].brand}, ${transactionsListData[i].name} - (${transactionsListData[i].year})',
                                  textAlign: TextAlign.left,
                                  style: TextStyle(
                                    fontFamily: AppTheme.fontName,
                                    fontWeight: FontWeight.bold,
                                    fontSize: 15,
                                    letterSpacing: 0.2,
                                    color: AppTheme.darkText,
                                  ),
                                ),
                                SizedBox(height: 8),
                                Row(
                                  mainAxisAlignment:
                                      MainAxisAlignment.spaceBetween,
                                  children: <Widget>[
                                    Text(
                                      '\$${transactionsListData[i].price}',
                                      style: TextStyle(
                                        fontSize: 13.8,
                                        fontWeight: FontWeight.bold,
                                        color: AppTheme.dark_grey,
                                      ),
                                    ),
                                  ],
                                ),
                                SizedBox(height: 8),
                                Row(
                                  crossAxisAlignment: CrossAxisAlignment.start,
                                  children: [
                                    Container(
                                      width: 100,
                                      decoration: BoxDecoration(
                                        color: _getStatusColor(
                                            transactionsListData[i].status),
                                        borderRadius:
                                            BorderRadius.circular(4.0),
                                      ),
                                      padding: EdgeInsets.symmetric(
                                          vertical: 2, horizontal: 4),
                                      child: Center(
                                        child: Row(
                                          mainAxisAlignment:
                                              MainAxisAlignment.center,
                                          children: [
                                            Icon(
                                              Icons.info,
                                              color: AppTheme.white
                                                  .withOpacity(0.8),
                                              size: 15,
                                            ),
                                            SizedBox(width: 4),
                                            Text(
                                              '${transactionsListData[i].status}',
                                              style: TextStyle(
                                                fontSize: 13,
                                                color: AppTheme.white
                                                    .withOpacity(0.8),
                                              ),
                                            ),
                                          ],
                                        ),
                                      ),
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
              ),
            );
          },
        ),
      );
    }
    return widgets;
  }

  Color _getStatusColor(String status) {
    switch (status.toLowerCase()) {
      case 'pending':
        return Color.fromARGB(255, 32, 130, 242);
      case 'accepted':
        return const Color.fromARGB(255, 75, 175, 92);
      case 'canceled':
        return const Color.fromARGB(255, 245, 55, 55);
      default:
        return AppTheme.grey.withOpacity(0.8);
    }
  }
}
