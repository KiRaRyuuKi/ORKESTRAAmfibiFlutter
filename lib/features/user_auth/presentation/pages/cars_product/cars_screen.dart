import 'package:flutter/material.dart';
import 'package:Amfibi_App/features/user_auth/presentation/pages/Message/inquiry_page.dart';
import 'package:Amfibi_App/features/user_auth/presentation/widgets/product_widgets/information.dart';
import 'package:Amfibi_App/features/user_auth/presentation/widgets/product_widgets/product_desc.dart';
import 'package:Amfibi_App/features/user_auth/presentation/pages/model/cars.dart';

class CarsScreen extends StatefulWidget {
  final Cars product;

  const CarsScreen({Key? key, required this.product}) : super(key: key);

  @override
  _CarsDetailState createState() => _CarsDetailState();
}

class _CarsDetailState extends State<CarsScreen> {
  int currentImage = 0;
  int currentColor = 0;
  int currentNumber = 1;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        title: TextField(
          decoration: InputDecoration(
            hintText: 'Search...',
            border: InputBorder.none,
            prefixIcon: Icon(Icons.search),
          ),
        ),
        actions: [
          IconButton(
            icon: Icon(Icons.shopping_cart),
            onPressed: () {},
          ),
        ],
      ),
      floatingActionButton: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 20.0),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Expanded(
              child: ElevatedButton(
                onPressed: () {
                  // Add to cart action
                },
                child: Text("Add to Cart"),
                style: ElevatedButton.styleFrom(
                  primary: Colors.orange,
                  padding: EdgeInsets.symmetric(vertical: 15),
                ),
              ),
            ),
            SizedBox(width: 10),
            Expanded(
              child: ElevatedButton(
                onPressed: () {
                  // Buy now action
                },
                child: Text("Buy Now"),
                style: ElevatedButton.styleFrom(
                  primary: Colors.red,
                  padding: EdgeInsets.symmetric(vertical: 15),
                ),
              ),
            ),
            SizedBox(width: 10),
            Expanded(
              child: ElevatedButton(
                onPressed: () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (context) => InquiryFormPage(
                        productName: widget.product.carName,
                        productDescription: widget.product.description,
                      ),
                    ),
                  );
                },
                child: Text("Inquire"),
                style: ElevatedButton.styleFrom(
                  primary: Colors.blue,
                  padding: EdgeInsets.symmetric(vertical: 15),
                ),
              ),
            ),
          ],
        ),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat,
      body: SafeArea(
        child: SingleChildScrollView(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Container(
                height: 300,
                child: PageView.builder(
                  onPageChanged: (index) {
                    setState(() {
                      currentImage = index;
                    });
                  },
                  itemCount: widget.product.image.length,
                  itemBuilder: (context, index) {
                    return Image.network(
                      widget.product.image[index],
                      errorBuilder: (context, error, stackTrace) {
                        return Center(
                          child: Text('Image failed to load'),
                        );
                      },
                    );
                  },
                ),
              ),
              const SizedBox(height: 10),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: List.generate(
                  widget.product.image.length,
                  (index) => AnimatedContainer(
                    duration: const Duration(milliseconds: 300),
                    width: currentImage == index ? 15 : 8,
                    height: 8,
                    margin: const EdgeInsets.only(right: 2),
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(10),
                      color: currentImage == index ? Colors.black : Colors.grey,
                    ),
                  ),
                ),
              ),
              const SizedBox(height: 20),
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 20),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    ProductInfo(product: widget.product),
                    const SizedBox(height: 20),
                    const Text(
                      "Color",
                      style: TextStyle(
                        fontSize: 18,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    const SizedBox(height: 10),
                    SingleChildScrollView(
                      scrollDirection: Axis.horizontal,
                      child: Row(
                        children: List.generate(
                          widget.product.colors.length,
                          (index) => GestureDetector(
                            onTap: () {
                              setState(() {
                                currentColor = index;
                              });
                            },
                            child: Padding(
                              padding: const EdgeInsets.only(right: 15),
                              child: Container(
                                width: 30,
                                height: 30,
                                decoration: BoxDecoration(
                                  color: widget.product.colors[index],
                                  shape: BoxShape.circle,
                                  border: currentColor == index
                                      ? Border.all(
                                          color: Colors.black,
                                          width: 2,
                                        )
                                      : null,
                                ),
                              ),
                            ),
                          ),
                        ),
                      ),
                    ),
                    const SizedBox(height: 20),
                    ProductDescription(text: widget.product.description),
                    const SizedBox(height: 20),
                    const Text(
                      "Reviews",
                      style: TextStyle(
                        fontSize: 18,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    const SizedBox(height: 10),
                    Column(
                      children: [
                        ListTile(
                          leading: Icon(Icons.person),
                          title: Text("John Doe"),
                          subtitle: Text("Great product!"),
                          trailing: Row(
                            mainAxisSize: MainAxisSize.min,
                            children: List.generate(
                                5,
                                (index) => Icon(
                                      index < 4
                                          ? Icons.star
                                          : Icons.star_border,
                                      color: Colors.amber,
                                    )),
                          ),
                        ),
                        ListTile(
                          leading: Icon(Icons.person),
                          title: Text("Jane Doe"),
                          subtitle: Text("Good value for money."),
                          trailing: Row(
                            mainAxisSize: MainAxisSize.min,
                            children: List.generate(
                                5,
                                (index) => Icon(
                                      index < 3
                                          ? Icons.star
                                          : Icons.star_border,
                                      color: Colors.amber,
                                    )),
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
    );
  }
}
