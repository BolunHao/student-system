<template>
  <div class="programs-page">
    <h2>Programs</h2>
 
    <!-- Introduction content -->
    <div class="introduction">
      <h2>Bring academic theory to life</h2>
      <p>
        Studying for an MEng at the Dyson Institute gives you the unique opportunity to study for your degree at the centre of a global leader in design innovation. You’ll learn engineering fundamentals from leading academics and put them into practice developing future Dyson products.
      </p>
      <p>
        Twenty-first century engineering demands multidisciplinary experts. In your first two years you'll study a general engineering syllabus. Designed to be academically rigorous, and covering engineering fundamentals, theory is paired with real life learning as you rotate through many of Dyson's global engineering teams. You'll gain a strong academic and technical foundation across:
      </p>
      <ul>
        <li>Software engineering</li>
        <li>Mechanical engineering</li>
        <li>Electrical and electronics engineering</li>
        <li>Electromechanical engineering</li>
      </ul>
      <p>
        Building on these broad skillsets, in years three and four you'll choose to specialise in one of these areas. Whilst this can be challenging, we support you to find the specialism that best aligns with your skills and interests. With your unique skillset you'll have opportunities to develop your expertise and become an invaluable part of your team.
      </p>
      <p>
        <strong>Please note:</strong> Offering the MEng is subject to confirmation of Full Taught Degree Awarding Powers by the OfS which is expected in Spring 2024. In the event of a delay in this, offers will be made to the BEng Engineering programme. Applicants will be kept informed as appropriate throughout the process and confirmation of the programme will be given before offers are made.
      </p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Programs'
};
</script>

<style>
.programs-page {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.introduction {
  max-width: 1000px; /* Limit the maximum width of the introduction content */
  text-align: left; /* Left-justified text */
  margin-top: 20px; /* Adjust top spacing */
  margin-left : 15%;
}
</style>
