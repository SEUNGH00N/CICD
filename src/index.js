import express from 'express';
import bodyParser from 'body-parser';
import dotenv from 'dotenv';
import userRoutes from './routes/userRoutes';
import productRoutes from './routes/productRoutes';
import cors from 'cors';

dotenv.config();

const app = express();
const port = process.env.PORT || 4000;

app.use(cors());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

app.use('/uploads', express.static('uploads'));
app.use('/uploads_id', express.static('uploads_id'));

// Routes
app.use('/users', userRoutes);
app.use('/product', productRoutes);

app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
