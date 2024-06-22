import 'package:flutter/material.dart';

class ProfileView extends StatefulWidget {
  final AnimationController? animationController;
  final Animation<double>? animation;

  const ProfileView({Key? key, this.animationController, this.animation})
      : super(key: key);

  @override
  _ProfileViewState createState() => _ProfileViewState();
}

class _ProfileViewState extends State<ProfileView> {
  TextEditingController _nameController = TextEditingController(text: '');
  TextEditingController _emailController = TextEditingController(text: '');
  TextEditingController _addressController = TextEditingController(text: '');
  TextEditingController _phoneNumberController =
      TextEditingController(text: '');
  TextEditingController _kkNumberController = TextEditingController(text: '');
  TextEditingController _ktpNumberController = TextEditingController(text: '');

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
              30 * (1.0 - widget.animation!.value),
              0.0,
            ),
            child: Padding(
              padding: const EdgeInsets.symmetric(horizontal: 24, vertical: 16),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Container(
                    margin: EdgeInsets.symmetric(horizontal: 5.0),
                    padding: EdgeInsets.all(18.0),
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(12.0),
                      color: Colors.white,
                      boxShadow: [
                        BoxShadow(
                          color: Colors.grey.withOpacity(0.3),
                          spreadRadius: 2,
                          blurRadius: 5,
                          offset: Offset(0, 3),
                        ),
                      ],
                    ),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Padding(
                          padding: const EdgeInsets.only(bottom: 22.0),
                          child: Text(
                            'Profile Information',
                            style: TextStyle(
                              fontSize: 18,
                              fontWeight: FontWeight.bold,
                              color: Colors.blue[800],
                            ),
                          ),
                        ),
                        _buildEditableProfileItem(
                            'Name', _nameController, Icons.person),
                        _buildEditableProfileItem(
                            'Email', _emailController, Icons.email),
                        _buildEditableProfileItem(
                            'Address', _addressController, Icons.location_on),
                        _buildEditableProfileItem('Phone Number',
                            _phoneNumberController, Icons.phone),
                        _buildEditableProfileItem('KK Number',
                            _kkNumberController, Icons.credit_card),
                        _buildEditableProfileItem('KTP Number',
                            _ktpNumberController, Icons.perm_identity),
                      ],
                    ),
                  ),
                  SizedBox(height: 28.0),
                  Padding(
                    padding: const EdgeInsets.symmetric(horizontal: 5.0),
                    child: ElevatedButton(
                      onPressed: () {
                        _saveChanges();
                      },
                      style: ElevatedButton.styleFrom(
                        backgroundColor: Colors.blue[800],
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(8.0),
                        ),
                      ),
                      child: Padding(
                        padding: const EdgeInsets.symmetric(vertical: 12.0),
                        child: SizedBox(
                          width: double.infinity,
                          child: Center(
                            child: Text(
                              'Konfirmasi Edit',
                              style: TextStyle(
                                fontSize: 16.0,
                                fontWeight: FontWeight.bold,
                                color: Colors.white,
                              ),
                            ),
                          ),
                        ),
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ),
        );
      },
    );
  }

  Widget _buildEditableProfileItem(
      String title, TextEditingController controller, IconData icon) {
    return Container(
      margin: EdgeInsets.only(bottom: 16.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Row(
            children: [
              Icon(
                icon,
                size: 20,
                color: Colors.blue[800],
              ),
              SizedBox(width: 12.0),
              Text(
                title,
                style: TextStyle(
                  fontWeight: FontWeight.bold,
                  fontSize: 14,
                  color: Colors.blue[800],
                ),
              ),
            ],
          ),
          SizedBox(height: 8.0),
          TextFormField(
            controller: controller,
            decoration: InputDecoration(
              isDense: true,
              contentPadding: EdgeInsets.symmetric(
                vertical: 12.0,
                horizontal: 12.0,
              ),
              border: OutlineInputBorder(
                borderRadius: BorderRadius.circular(8.0),
                borderSide: BorderSide(
                  color: Colors.grey.shade400,
                  width: 1.0,
                ),
              ),
              focusedBorder: OutlineInputBorder(
                borderRadius: BorderRadius.circular(8.0),
                borderSide: BorderSide(
                  color: Colors.lightBlueAccent,
                  width: 1.0,
                ),
              ),
              enabledBorder: OutlineInputBorder(
                borderRadius: BorderRadius.circular(8.0),
                borderSide: BorderSide(
                  color: Colors.grey.shade400,
                  width: 1.0,
                ),
              ),
            ),
            style: TextStyle(
              fontSize: 16,
            ),
          ),
        ],
      ),
    );
  }

  void _saveChanges() {
    print('Name: ${_nameController.text}');
    print('Email: ${_emailController.text}');
    print('Address: ${_addressController.text}');
    print('Phone Number: ${_phoneNumberController.text}');
    print('KK Number: ${_kkNumberController.text}');
    print('KTP Number: ${_ktpNumberController.text}');
  }

  @override
  void dispose() {
    _nameController.dispose();
    _emailController.dispose();
    _addressController.dispose();
    _phoneNumberController.dispose();
    _kkNumberController.dispose();
    _ktpNumberController.dispose();
    super.dispose();
  }
}
